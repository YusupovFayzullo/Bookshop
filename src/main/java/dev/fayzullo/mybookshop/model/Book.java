package dev.fayzullo.mybookshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {


    private Integer id;
    private String title;
    private String author;
    private String publisher;
    private String description;
    private LocalDate publishedAt;
    private String url;
    private int pages;
    private int downloads;
    private int views;
    private int likes;
    private int dislikes;
    private Integer category;
    private boolean deleted;
    private Integer coverId;
    private Integer documentId;
}
