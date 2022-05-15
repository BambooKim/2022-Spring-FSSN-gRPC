package com.bamboo.grpc;

import io.grpc.stub.StreamObserver;

import static com.bamboo.grpc.Serverstreaming.*;
import static com.bamboo.grpc.Serverstreaming.Number;

public class ServerStreamingImpl extends ServerStreamingGrpc.ServerStreamingImplBase {
    @Override
    public void getServerResponse(Number request, StreamObserver<Message> responseObserver) {
        int received = request.getValue();

        System.out.println("received = " + received);

        for (int i = 1; i <= received; i++) {
            String message = "message #" + i;
            Message reply = Message.newBuilder()
                    .setMessage(message)
                    .build();
            responseObserver.onNext(reply);
        }
        responseObserver.onCompleted();
    }
}
