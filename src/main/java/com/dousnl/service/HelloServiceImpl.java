package com.dousnl.service;


import com.dousnl.HelloRequest;
import com.dousnl.HelloResponse;
import com.dousnl.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        HelloResponse response = HelloResponse.newBuilder().setResponse("Hello,服务端返回： " + request.getName()).setCode1(111).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
