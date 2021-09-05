package com.publishing.house.bookcatalog.DTO;

import com.publishing.house.bookcatalog.model.Author;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorDTO {
    private Author author;
    private double averageRating;


}
