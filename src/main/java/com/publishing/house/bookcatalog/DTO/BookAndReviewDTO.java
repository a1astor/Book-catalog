package com.publishing.house.bookcatalog.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookAndReviewDTO {
    private Long isbn;
    private Long reviewId;
}
