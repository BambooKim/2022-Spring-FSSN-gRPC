package com.bamboo.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class ClientMain {
    public static void main(String[] args) {
        String address = "localhost";
        int port = 8080;

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(address, port)
                .usePlaintext()
                .build();

        BidirectionalGrpc.BidirectionalStub asyncStub = BidirectionalGrpc.newStub(channel);

        StreamObserver<BidirectionalOuterClass.Message> responseObserver = new StreamObserver<BidirectionalOuterClass.Message>() {
            @Override
            public void onNext(BidirectionalOuterClass.Message value) {
                System.out.println("[server to client] " + value.getMessage());
            }

            @Override
            public void onError(Throwable t) {
                Status status = Status.fromThrowable(t);
                System.err.println(status);
            }

            @Override
            public void onCompleted() {
                channel.shutdown();
                System.out.println("Client Shutdown");
            }
        };

        StreamObserver<BidirectionalOuterClass.Message> requestObserver = asyncStub.getServerResponse(responseObserver);
        for (int i = 1; i <= 5; i++) {
            String message = "message #" + i;
            requestObserver.onNext(BidirectionalOuterClass.Message.newBuilder()
                    .setMessage(message)
                    .build());
            System.out.println("[client to server] " + message);
        }
        requestObserver.onCompleted();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
