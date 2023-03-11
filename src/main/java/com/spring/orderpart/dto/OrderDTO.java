package com.spring.orderpart.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDTO
{
    private LocalDate date;
    private long price;
    private long quantity;
    private String address;
    private Long user;
    private Long book;
    private boolean cancel;
    public boolean getCancel()
    {
        return cancel;
    }
}
