package server;

import com.endpoints.examples.bookstore.BookServiceGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class BookStoreServer {
    private static final Logger logger = Logger.getLogger(BookStoreServer.class.getName());

    private Server server;

    /**
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        final BookStoreServer server = new BookStoreServer();
        server.start();
        server.blockUntilShutdown();
    }

    public void start() throws IOException {
        /* The port on which the server should run */
        int port = 50055;
        server = ServerBuilder.forPort(port)
                .addService(new BookStoreServiceImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                try {
                    BookStoreServer.this.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
                System.err.println("*** server shut down");
            }
        });
    }

    public void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    static class BookStoreServiceImpl extends BookServiceGrpc.BookServiceImplBase {

        @Override
        public void getBook(com.endpoints.examples.bookstore.GetBookRequest request, StreamObserver<com.endpoints.examples.bookstore.BookResponse> responseObserver) {

            com.endpoints.examples.bookstore.BookResponse.Builder response = com.endpoints.examples.bookstore.BookResponse.newBuilder();

            int isbn = request.getIsbn();

            if (isbn == 1) {
                response.setResponseCode("200").setMessage("Success").build();
            } else {
                response.setResponseCode("200").setMessage("Failed").build();
            }

            responseObserver.onNext(response.build());

            responseObserver.onCompleted();
        }

        @Override
        public void getBooksViaAuthor(com.endpoints.examples.bookstore.BookAuthorRequest request, StreamObserver<com.endpoints.examples.bookstore.BookResponse> responseObserver) {
            com.endpoints.examples.bookstore.BookResponse.Builder response = com.endpoints.examples.bookstore.BookResponse.newBuilder();

            String author = request.getAuthor();

            System.out.println("Author Request " + author);
            logger.info("Author name " + author);

            if (author.equals("Bob")) {
                response.setResponseCode("200").setMessage("Success").build();
            } else
                response.setResponseCode("200").setMessage("Failed").build();

            responseObserver.onNext(response.build());

            responseObserver.onCompleted();
        }
    }
}
