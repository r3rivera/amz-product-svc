package com.boogeyman.app.graphql.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Data
public class Author {

    private String id;
    private String firstName;
    private String lastName;

    private static List<Author> authors = Arrays.asList(
            new Author("author-1", "Joshua", "Bloch"),
            new Author("author-2", "Douglas", "Adams"),
            new Author("author-3", "Bill", "Bryson")
            );

    public static Author getById(String id) {
            return authors.stream()
            .filter(author -> author.getId().equals(id))
            .findFirst()
            .orElse(null);
            }
}