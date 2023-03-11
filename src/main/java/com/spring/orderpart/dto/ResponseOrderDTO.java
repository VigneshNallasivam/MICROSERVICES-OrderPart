package com.spring.orderpart.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseOrderDTO
{
    private String message;
    private Object object;

    public ResponseOrderDTO(String message,Object object)
    {
        this.message=message;
        this.object=object;
    }

}
