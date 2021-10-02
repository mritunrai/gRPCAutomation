package com.grpc.book;

import client.BookClient;
import com.book.grpc.BookResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookTests extends BaseTests {
    private BookClient bookClient;

    @Test
    public void getBookTests() {
        bookClient = new BookClient();

        BookResponse bookResponse = bookClient.getBookByISBN(1);

        Assert.assertEquals(bookResponse.getBook().getIsbn(), 1);
    }

    @Test
    public void getBookViaAuthor() {
        bookClient = new BookClient();

        BookResponse bookResponse = bookClient.getBookByAuthor("Bob");

        Assert.assertEquals(bookResponse.getBook().getAuthor(), "Bob");
    }
}
