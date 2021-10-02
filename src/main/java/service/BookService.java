package service;

import bookrepositary.BookRepositary;
import com.book.grpc.BookAuthorRequest;
import com.book.grpc.BookResponse;
import com.book.grpc.BookServiceGrpc.BookServiceImplBase;
import com.book.grpc.GetBookRequest;
import io.grpc.stub.StreamObserver;

import java.util.Map;

public class BookService extends BookServiceImplBase {
    Map<Integer, Map<String, String>> bookCollection = null;
    private BookRepositary bookRepositary;

    public BookService() {
        bookRepositary = new BookRepositary();
    }

    @Override
    public void getBook(GetBookRequest getBookRequest, StreamObserver<BookResponse> responseStreamObserver) {
        BookResponse.Builder response = BookResponse.newBuilder();
        Book book = new Book();
        book.setIsbn((int) getBookRequest.getIsbn());

        bookCollection = bookRepositary.getBookRepositary();

        Map<String, String> bookdDetails = bookCollection.get(getBookRequest.getIsbn());

        for (Map.Entry<String, String> set :
                bookdDetails.entrySet()) {

            book.setTitle(set.getKey());
            book.setAuthor(set.getValue());
        }

        if (!bookdDetails.isEmpty()) {
            response.setSuccess(true).setBook(book);
        } else
            response.setSuccess(false);
    }

    @Override
    public void getBooksViaAuthor(BookAuthorRequest getAuthorRequest, StreamObserver<BookResponse> responseStreamObserver) {
        BookResponse.Builder response = BookResponse.newBuilder();
        Book book = new Book();
        book.setAuthor(getAuthorRequest.getAuthor());

        bookCollection = bookRepositary.getBookRepositary();

        Map<String, String> bookedDetails = bookCollection.get(getAuthorRequest.getAuthor());

        {
            for (Map.Entry<Integer, Map<String, String>> entry : bookCollection.entrySet()) {
                book.setIsbn(entry.getKey());
                for (Map.Entry<String, String> author : entry.getValue().entrySet()) {
                    if (author.getValue().equals(getAuthorRequest.getAuthor())) {
                        book.setTitle(author.getKey());
                        book.setAuthor(author.getValue());
                        break;
                    }
                }
            }
        }

        if (!bookedDetails.isEmpty()) {
            response.setSuccess(true).setBook(book);
        } else
            response.setSuccess(false);
    }
}