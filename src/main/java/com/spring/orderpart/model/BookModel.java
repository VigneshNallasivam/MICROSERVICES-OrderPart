package com.spring.orderpart.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookModel
{
    private Long bookId;
    private String bookName;
    private String authorName;
    private String bookDescription;
    private String bookImg;
    private long price;
    private long quantity;
}
