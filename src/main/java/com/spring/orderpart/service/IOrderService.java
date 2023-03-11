package com.spring.orderpart.service;
import com.spring.orderpart.dto.OrderDTO;
import com.spring.orderpart.model.OrderModel;
import java.util.List;
public interface IOrderService
{
    OrderModel insert(OrderDTO orderDTO) throws Exception;
    List<OrderModel> getAll();
    OrderModel getById(long id);
    OrderModel updateById(long id, OrderDTO orderDTO);
    String deleteById(long orderId, long userId);

}
