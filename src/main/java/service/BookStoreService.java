package service;

import com.endpoints.examples.bookstore.BookAuthorRequest;
import com.endpoints.examples.bookstore.BookResponse;
import com.endpoints.examples.bookstore.BookServiceGrpc.BookServiceImplBase;
import com.endpoints.examples.bookstore.GetBookRequest;
import io.grpc.stub.StreamObserver;

public class BookStoreService extends BookServiceImplBase {

    @Override
    public void getBook(GetBookRequest request, StreamObserver<BookResponse> responseObserver) {
        super.getBook(request, responseObserver);

        BookResponse.Builder response = BookResponse.newBuilder();

        int isbn = request.getIsbn();

        if (isbn == 1) {
            response.setResponseCode("200").setMessage("Success");
        } else {
            response.setResponseCode("200").setMessage("Failed");
        }

        responseObserver.onCompleted();
    }

    @Override
    public void getBooksViaAuthor(BookAuthorRequest request, StreamObserver<BookResponse> responseObserver) {
        super.getBooksViaAuthor(request, responseObserver);

        BookResponse.Builder response = BookResponse.newBuilder();

        String author = request.getAuthor();

        if (author.equals("Bob")) {
            response.setResponseCode("200").setMessage("Success");
        } else
            response.setResponseCode("200").setMessage("Failed");
    }
}
