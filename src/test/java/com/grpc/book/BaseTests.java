package com.grpc.book;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import server.BookStoreServer;


import java.io.IOException;

public class BaseTests {

    @BeforeSuite(alwaysRun = true)
    public void GRPCSetUp() throws IOException, InterruptedException {
        BookStoreServer grpcServer = new BookStoreServer();
        grpcServer.start();
    }

//    @AfterSuite(alwaysRun = true)
//    public void GRPCTearDown() throws InterruptedException {
//        BookStoreServer grpcServer = new BookStoreServer();
//       // grpcServer.stop();
//    }
}
