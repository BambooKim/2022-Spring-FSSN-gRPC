package com.bamboo.grpc;

import com.google.protobuf.Value;
import com.google.rpc.context.AttributeContext;
import io.grpc.stub.StreamObserver;

public class MyServiceImpl extends MyServiceGrpc.MyServiceImplBase {
    @Override
    public void myFunction(HelloGrpc.MyNumber request, StreamObserver<HelloGrpc.MyNumber> responseObserver) {
        Integer value = request.getValue();

        System.out.println("Request received... Value = " + value);

        HelloGrpc.MyNumber response = HelloGrpc.MyNumber.newBuilder().setValue(value).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
