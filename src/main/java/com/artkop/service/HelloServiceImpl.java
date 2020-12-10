package com.artkop.service;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void hello(
            HelloRequest request, StreamObserver<HelloResponse> responseObserver) {

        log.info("message successfully delivered to gRC server");
        String greeting = new StringBuilder()
                .append("There is message sent from RabbitMQ to gRPC: , ")
                .append(request.getMessage())
                .toString();

        HelloResponse response = HelloResponse.newBuilder()
                .setGreeting(greeting)
                .build();

        log.info(greeting);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
