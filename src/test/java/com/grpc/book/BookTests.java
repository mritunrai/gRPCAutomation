package com.grpc.book;

import client.BookClient;
import com.endpoints.examples.bookstore.BookResponse;
import exception.AuthorNotFoundException;
import exception.BookNotFoundException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookTests {
    private BookClient bookClient;

    @Test
    public void getBookTests() throws BookNotFoundException {
        bookClient = new BookClient();

        BookResponse bookResponse = bookClient.getBookByISBN(1);

        Assert.assertEquals(bookResponse.getBook().getIsbn(), 1);
    }

    @Test
    public void getBookViaAuthor() throws AuthorNotFoundException {
        bookClient = new BookClient();

        BookResponse bookResponse = bookClient.getBookByAuthor("Bob");

        Assert.assertEquals(bookResponse.getBook().getAuthor(), "Bob");
    }
}
