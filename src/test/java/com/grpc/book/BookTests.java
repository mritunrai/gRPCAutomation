package com.grpc.book;

import client.BookClient;
import com.endpoints.examples.bookstore.BookResponse;
import exception.AuthorNotFoundException;
import exception.BookNotFoundException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookTests extends BaseTests {
    private BookClient bookClient;

    @Test
    public void getBookTests()  throws BookNotFoundException {
        bookClient = new BookClient();

        BookResponse bookResponse = bookClient.getBookByISBN(2);

        Assert.assertEquals(bookResponse.getResponseCode(), "200");
    }

    @Test
    public void getBookViaAuthor() throws AuthorNotFoundException {
        bookClient = new BookClient();

        BookResponse bookResponse = bookClient.getBookByAuthor("Bob1");

        Assert.assertEquals(bookResponse.getResponseCode(), "200");
    }
}
