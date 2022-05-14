package com.bamboo.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) {
        int port = 8080;
        Server server = ServerBuilder
                .forPort(port)
                .addService(new MyServiceImpl()).build();

        try {
            System.out.println("Server: Start Server Listening port " + port);

            server.start();
            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return;
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("Server: Shutting down gRPC Server");
            server.shutdown();
            System.err.println("Server: Server shut down");
        }));
    }
}
