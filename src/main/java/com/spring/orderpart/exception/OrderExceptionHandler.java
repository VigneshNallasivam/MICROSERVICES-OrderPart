package com.spring.orderpart.exception;

import com.spring.orderpart.dto.ResponseOrderDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class OrderExceptionHandler
{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseOrderDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex)
    {
        List<ObjectError> objectErrorList = ex.getBindingResult().getAllErrors();
        List<String> errorMsg = objectErrorList.stream().map(e->e.getDefaultMessage()).collect(Collectors.toList());
        ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO("Exception Occured..!!",errorMsg.toString());
        return new ResponseEntity<>(responseOrderDTO, HttpStatus.BAD_GATEWAY);
    }
    @ExceptionHandler(OrderException.class)
    public ResponseEntity<ResponseOrderDTO> handleException(OrderException ex)
    {
        ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO("Exception Occured..!!",ex.getMessage());
        return new ResponseEntity<>(responseOrderDTO, HttpStatus.BAD_REQUEST);

    }
}
