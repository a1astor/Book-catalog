package com.publishing.house.bookcatalog.model;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDTO {
    private Book book;
    private List<Integer> ratings;
}
