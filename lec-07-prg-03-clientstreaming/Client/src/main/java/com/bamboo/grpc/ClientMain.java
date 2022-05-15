package com.bamboo.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class ClientMain {
    public static void main(String[] args) {
        String address = "localhost";
        int port = 8080;

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(address, port)
                .usePlaintext()
                .build();

        ClientStreamingGrpc.ClientStreamingStub asyncStub = ClientStreamingGrpc.newStub(channel);

        StreamObserver<Clientstreaming.Number> responseObserver = new StreamObserver<>() {
            @Override
            public void onNext(Clientstreaming.Number value) {
                System.out.println("[server to client] " + value.getValue());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                channel.shutdown();
                System.out.println("Client Shutdown.");
            }
        };

        StreamObserver<Clientstreaming.Message> requestObserver = asyncStub.getServerResponse(responseObserver);
        for (int i = 1; i <= 5; i++) {
            String message = "message #" + i;
            requestObserver.onNext(Clientstreaming.Message.newBuilder()
                    .setMessage(message)
                    .build());
            System.out.println("[client to server] " + message);
        }
        requestObserver.onCompleted();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
