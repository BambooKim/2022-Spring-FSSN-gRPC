package com.bamboo.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ClientMain {
    public static void main(String[] args) {
        String address = "localhost";
        int port = 8080;

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(address, port)
                .usePlaintext()
                .build();

        MyServiceGrpc.MyServiceBlockingStub stub = MyServiceGrpc.newBlockingStub(channel);

        HelloGrpc.MyNumber response = stub.myFunction(HelloGrpc.MyNumber.newBuilder()
                .setValue(1234)
                .build());

        System.out.println("response = " + response);

        channel.shutdown();
    }
}
