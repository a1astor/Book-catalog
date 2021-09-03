package com.publishing.house.bookcatalog.DTO;


import java.util.List;

import com.publishing.house.bookcatalog.model.Book;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDTO {
    private Book book;
    private List<Integer> ratings;
}
