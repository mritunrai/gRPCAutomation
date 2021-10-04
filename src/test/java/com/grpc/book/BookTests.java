package com.grpc.book;

import client.BookClient;
import com.endpoints.examples.bookstore.BookResponse;
import exception.AuthorNotFoundException;
import exception.BookNotFoundException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookTests extends BaseTests {
    private BookClient bookClient;

    public BookTests() {
        bookClient = new BookClient();
    }

    @Test
    public void getBookTests() throws BookNotFoundException {
        BookResponse bookResponse = bookClient.getBookByISBN(1);

        Assert.assertEquals(bookResponse.getResponseCode(), "200");
    }

    @Test
    public void getBookViaAuthor() throws AuthorNotFoundException {
        BookResponse bookResponse = bookClient.getBookByAuthor("Bob");

        Assert.assertEquals(bookResponse.getResponseCode(), "200");
    }
}
