package com.bamboo.grpc;

import io.grpc.stub.StreamObserver;

public class ClientStreamingImpl extends ClientStreamingGrpc.ClientStreamingImplBase {
    @Override
    public StreamObserver<Clientstreaming.Message> getServerResponse(StreamObserver<Clientstreaming.Number> responseObserver) {
        System.out.println("Server processing gRPC client-streaming.");

        return new StreamObserver<Clientstreaming.Message>() {
            int count = 0;

            @Override
            public void onNext(Clientstreaming.Message value) {
                if (value != null) {
                    count++;
                }
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                System.out.println("Server to Client - count = " + count);

                responseObserver.onNext(Clientstreaming.Number.newBuilder()
                        .setValue(count)
                        .build());
                responseObserver.onCompleted();
            }
        };
    }
}
