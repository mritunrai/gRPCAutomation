package client;


import com.endpoints.examples.bookstore.BookServiceGrpc;
import exception.AuthorNotFoundException;
import exception.BookNotFoundException;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class BookClient {
    ManagedChannel channel;

    BookServiceGrpc.BookServiceBlockingStub bookServiceStub;

    public BookClient() {
        channel = ManagedChannelBuilder.forAddress("localhost", 50055).usePlaintext().build();
        bookServiceStub = BookServiceGrpc.newBlockingStub(channel);
    }

    public com.endpoints.examples.bookstore.BookResponse getBookByISBN(Integer ISBN) throws BookNotFoundException {
        try {
            com.endpoints.examples.bookstore.GetBookRequest getBookRequest = com.endpoints.examples.bookstore.GetBookRequest.newBuilder().setIsbn(ISBN).build();
            return bookServiceStub.getBook(getBookRequest);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new BookNotFoundException("Book not found");
        }
    }

    public com.endpoints.examples.bookstore.BookResponse getBookByAuthor(String authorName) throws AuthorNotFoundException {
        try {
            com.endpoints.examples.bookstore.BookAuthorRequest getBookRequest = com.endpoints.examples.bookstore.BookAuthorRequest.newBuilder().setAuthor(authorName).build();
            return bookServiceStub.getBooksViaAuthor(getBookRequest);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new AuthorNotFoundException("Author details not found ");
        }
    }
}
