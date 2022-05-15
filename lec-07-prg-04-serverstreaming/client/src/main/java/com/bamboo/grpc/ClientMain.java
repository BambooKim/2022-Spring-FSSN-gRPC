package com.bamboo.grpc;

import com.bamboo.grpc.Serverstreaming.Message;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

import static com.bamboo.grpc.ServerStreamingGrpc.*;

public class ClientMain {
    public static void main(String[] args) {
        String address = "localhost";
        int port = 8080;

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(address, port)
                .usePlaintext()
                .build();

        ServerStreamingBlockingStub blockingStub = newBlockingStub(channel);

        int requestValue = 7;
        Serverstreaming.Number request = Serverstreaming.Number.newBuilder()
                .setValue(requestValue)
                .build();

        Iterator<Message> serverResponse = blockingStub.getServerResponse(request);
        for (int i = 1; serverResponse.hasNext(); i++) {
            Message message = serverResponse.next();
            System.out.println("[server to client] " + message.getMessage());
        }

        channel.shutdown();
    }
}
