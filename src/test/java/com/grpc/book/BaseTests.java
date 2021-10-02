package com.grpc.book;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import server.GrpcServer;

import java.io.IOException;

public class BaseTests {

    @BeforeSuite(alwaysRun = true)
    public void GRPCSetUp() throws IOException, InterruptedException {
        GrpcServer grpcServer = new GrpcServer();
        grpcServer.createServer();
    }

    @AfterSuite(alwaysRun = true)
    public void GRPCTearDown() {
        GrpcServer grpcServer = new GrpcServer();
        grpcServer.terminateServer();
    }
}
