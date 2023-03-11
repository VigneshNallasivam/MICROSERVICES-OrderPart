package com.spring.orderpart.controller;
import com.spring.orderpart.dto.OrderDTO;
import com.spring.orderpart.dto.ResponseOrderDTO;
import com.spring.orderpart.model.OrderModel;
import com.spring.orderpart.service.IOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/homeorder")
public class OrderController
{
    @Autowired
    IOrderService orderService;
    @PostMapping("/insert")
    public ResponseEntity<ResponseOrderDTO> insert(@Valid @RequestBody OrderDTO orderDTO) throws Exception
    {
        OrderModel order=orderService.insert(orderDTO);
        ResponseOrderDTO responseOrderDTO=new ResponseOrderDTO("Details Added & submitted!",order);
        return new ResponseEntity<>(responseOrderDTO, HttpStatus.CREATED);
    }
    @GetMapping("/getAll")
    public ResponseEntity<ResponseOrderDTO> getAll()
    {
        List<OrderModel> orderList=orderService.getAll();
        ResponseOrderDTO responseOrderDTO=new ResponseOrderDTO("founded all!",orderList);
        return new ResponseEntity<>(responseOrderDTO,HttpStatus.FOUND);
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseOrderDTO> getById(@PathVariable Long id)
    {
        OrderModel order=orderService.getById(id);
        ResponseOrderDTO responseOrderDTO=new ResponseOrderDTO("founded!",order);
        return new ResponseEntity<>(responseOrderDTO,HttpStatus.FOUND);
    }
    @DeleteMapping("/deleteById/{orderId}/{userId}")
    public ResponseEntity<ResponseOrderDTO> deleteById(@PathVariable Long orderId, @PathVariable Long userId)
    {
        String result=orderService.deleteById(orderId,userId);
        ResponseOrderDTO responseOrderDTO=new ResponseOrderDTO("deleted!",result);
        return new ResponseEntity<>(responseOrderDTO,HttpStatus.GONE);
    }
    @PutMapping("/updateById/{id}")
    public ResponseEntity<ResponseOrderDTO> updateById(@Valid @PathVariable Long id,@RequestBody OrderDTO orderDTO)
    {
        OrderModel order=orderService.updateById(id,orderDTO);
        ResponseOrderDTO responseOrderDTO=new ResponseOrderDTO("updated!",order);
        return new ResponseEntity<>(responseOrderDTO,HttpStatus.ACCEPTED);
    }
}
