package dev.fayzullo.mybookshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BookDetailsDTO {
    private int count;
    private Integer id;
    private String title;
    private String author;
    private String description;
    private int views;
    private int likes;
    private int dislikes;
    private int downloads;
    private int pages;
    private String category;
    private String url;
    private String publisher;
    private String coverOriginalFileName;
    private String coverGeneratedFileName;
    private String coverFileSize;
    private String documentOriginalFileName;
    private String documentGeneratedFileName;
    private String documentFileSize;
    private  LocalDate publishedAt;

}
