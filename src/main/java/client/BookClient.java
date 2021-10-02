package client;

import com.book.grpc.BookAuthorRequest;
import com.book.grpc.BookResponse;
import com.book.grpc.BookServiceGrpc;
import com.book.grpc.BookServiceGrpc.BookServiceBlockingStub;
import com.book.grpc.GetBookRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class BookClient {
    ManagedChannel channel;

    BookServiceBlockingStub bookServiceStub;

    public BookClient() {
        channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();
        bookServiceStub = BookServiceGrpc.newBlockingStub(channel);
    }

    public BookResponse getBookByISBN(Integer ISBN) {
        GetBookRequest getBookRequest = GetBookRequest.newBuilder().setIsbn(ISBN).build();
        return bookServiceStub.getBook(getBookRequest);
    }

    public BookResponse getBookByAuthor(String authorName) {
        BookAuthorRequest getBookRequest = BookAuthorRequest.newBuilder().setAuthor(authorName).build();
        return bookServiceStub.getBooksViaAuthor(getBookRequest);
    }
}
