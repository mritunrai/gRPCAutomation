package server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import service.BookService;

import java.io.IOException;

public class GrpcServer {

    Server server;

    public static void main(String[] args) throws IOException, InterruptedException {
        GrpcServer grpcServer = new GrpcServer();
        grpcServer.createServer();
    }

    public void createServer() throws IOException, InterruptedException {
        server = ServerBuilder.forPort(9090).addService(new BookService()).build();

        server.start();

        System.out.println("Server started :" + server.getPort());
       server.awaitTermination();
    }

    public void terminateServer() {
        if (!server.isTerminated())
            server.shutdown();
    }
}
