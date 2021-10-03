package service;

import bookrepositary.BookRepositary;
import com.endpoints.examples.bookstore.Book;
import com.endpoints.examples.bookstore.BookResponse;
import com.endpoints.examples.bookstore.BookServiceGrpc;
import com.endpoints.examples.bookstore.GetBookRequest;
import io.grpc.stub.StreamObserver;
import com.endpoints.examples.bookstore.Book;

import java.util.Map;

public class BookService extends BookServiceGrpc.BookServiceImplBase {
    Map<Integer, Map<String, String>> bookCollection = null;
    private BookRepositary bookRepositary;
    private com.endpoints.examples.bookstore.Book book;

    public BookService() {
        bookRepositary = new BookRepositary();
    }

    @Override
    public void getBook(GetBookRequest getBookRequest, StreamObserver<BookResponse> responseStreamObserver) {
        BookResponse.Builder response = BookResponse.newBuilder();
        book = Book.newBuilder().setIsbn(getBookRequest.getIsbn()).build();

//        bookCollection = bookRepositary.getBookRepositary();
//
//        Map<String, String> bookDetails = bookCollection.get(getBookRequest.getIsbn());
//
//        for (Map.Entry<String, String> set :
//                bookDetails.entrySet()) {
//
//            book.toBuilder().setTitle(set.getKey()).build();
//            book.toBuilder().setAuthor(set.getValue()).build();
//        }

        if (!getBookRequest.getIsbn().isEmpty()) {
            response.setResponseCode("200").setMessage("Success");
        } else {
            response.setResponseCode("200").setMessage("Failed");
        }
    }

//    @Override
//    public void getBook(GetBookRequest getBookRequest, StreamObserver<BookResponse> responseStreamObserver) {
//        BookResponse.Builder response = BookResponse.newBuilder();
//        book = Book.newBuilder().setIsbn(getBookRequest.getIsbn()).build();
//
//        bookCollection = bookRepositary.getBookRepositary();
//
//        Map<String, String> bookDetails = bookCollection.get(getBookRequest.getIsbn());
//
//        for (Map.Entry<String, String> set :
//                bookDetails.entrySet()) {
//
//            book.toBuilder().setTitle(set.getKey()).build();
//            book.toBuilder().setAuthor(set.getValue()).build();
//        }
//        if (!bookDetails.isEmpty()) {
//            response.setResponseCode("200").setMessage("Success").setBook(book);
//        } else {
//            response.setResponseCode("200").setMessage("Failed");
//        }
//    }Failed

    @Override
    public void getBooksViaAuthor(com.endpoints.examples.bookstore.BookAuthorRequest getAuthorRequest, StreamObserver<BookResponse> responseStreamObserver) {
        com.endpoints.examples.bookstore.BookResponse.Builder response = BookResponse.newBuilder();
        book = Book.newBuilder().setAuthor(getAuthorRequest.getAuthor()).build();

        bookCollection = bookRepositary.getBookRepositary();

//        Map<String, String> bookedDetails = bookCollection.get(getAuthorRequest.getAuthor());
//
//        {
//            for (Map.Entry<Integer, Map<String, String>> entry : bookCollection.entrySet()) {
//                book = Book.newBuilder().setIsbn(entry.getKey()).build();
//                for (Map.Entry<String, String> author : entry.getValue().entrySet()) {
//                    if (author.getValue().equals(getAuthorRequest.getAuthor())) {
//                        book.toBuilder().setTitle(author.getKey()).build();
//                        book.toBuilder().setAuthor(author.getValue()).build();
//                        break;
//                    }
//                }
//            }
//        }

        if (!getAuthorRequest.getAuthor().isEmpty()) {
            response.setResponseCode("200").setMessage("Success");
        } else
            response.setResponseCode("200").setMessage("Failed");
    }
}