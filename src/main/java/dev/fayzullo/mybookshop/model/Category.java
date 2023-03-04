package dev.fayzullo.mybookshop.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    private Long id;
    private String name;

    public Category(String name) {
        this.name = name;
    }


}
