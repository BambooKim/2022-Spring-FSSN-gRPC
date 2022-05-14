package com.bamboo.grpc;

import io.grpc.stub.StreamObserver;

public class BidirectionalImpl extends BidirectionalGrpc.BidirectionalImplBase {
    @Override
    public StreamObserver<BidirectionalOuterClass.Message> getServerResponse(final StreamObserver<BidirectionalOuterClass.Message> responseObserver) {
        System.out.println("Server Processing gRPC bidirectional streaming.");

        return new StreamObserver<BidirectionalOuterClass.Message>() {
            @Override
            public void onNext(BidirectionalOuterClass.Message value) {
                System.out.println("Received Message: " + value.getMessage());

                BidirectionalOuterClass.Message response = BidirectionalOuterClass.Message.newBuilder()
                        .setMessage(value.getMessage())
                        .build();

                responseObserver.onNext(response);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
