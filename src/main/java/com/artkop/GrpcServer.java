package com.artkop;


import com.artkop.service.HelloServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GrpcServer {


    @SneakyThrows
    public static void main(String[] args) {
        log.info("started");
        Server server = ServerBuilder
                .forPort(8081)
                .addService(new HelloServiceImpl()).build();

        log.info("formed");
        server.start();

        log.info("processing");
        server.awaitTermination();
    }
}
