package com.boogeyman.app.graphql.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Data
public class Book{

    private String id;
    private String name;
    private int pageCount;
    private String authorId;

    private static List<Book> books = Arrays.asList(
            new Book("book-1", "Effective Java", 416, "author-1"),
            new Book("book-2", "Hitchhiker's Guide to the Galaxy", 208, "author-2"),
            new Book("book-3", "Down Under", 436, "author-3")
            );

    public static Book getById(String id) {
            return books.stream()
            .filter(book -> book.getId().equals(id))
            .findFirst()
            .orElse(null);
            }
}
