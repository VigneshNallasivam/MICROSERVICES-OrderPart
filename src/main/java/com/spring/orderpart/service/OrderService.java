package com.spring.orderpart.service;
import com.spring.orderpart.dto.OrderDTO;
import com.spring.orderpart.exception.OrderException;
import com.spring.orderpart.model.BookModel;
import com.spring.orderpart.model.OrderModel;
import com.spring.orderpart.model.UserModel;
import com.spring.orderpart.repository.OrderRepository;
import com.spring.orderpart.utility.EmailSenderService;
import com.spring.orderpart.utility.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;
@Service
public class OrderService implements IOrderService
{
    @Autowired
    OrderRepository orderRepo;
    @Autowired
    RestTemplate restTemp;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    EmailSenderService emailSenderService;

    @Override
    public OrderModel insert(OrderDTO orderDTO) throws Exception
    {
        UserModel userModel = restTemp.getForObject("http://localhost:8081/homeuser/getById/1" + orderDTO.getUser(), UserModel.class);
        BookModel bookModel = restTemp.getForObject("http://localhost:8082/homebook/getById/1" + orderDTO.getBook(), BookModel.class);
        long price = orderDTO.getPrice() * orderDTO.getQuantity();
        if (userModel != null && bookModel != null)
        {
            OrderModel orderModel = new OrderModel(orderDTO.getUser(),orderDTO.getBook(),price,orderDTO.getQuantity(), orderDTO.getAddress(), orderDTO.getCancel());
            orderRepo.save(orderModel);
            String token = tokenUtil.createToken(orderModel.getOrderId());
            emailSenderService.sendMail(userModel.getEmail(), "Order Placed!!", "Your order has been placed successfully! Order Token Is : " + token);
            return orderModel;
        }
        else
        {
            throw new OrderException("Order not placed..!!");
        }
    }

    @Override
    public List<OrderModel> getAll()
    {
        List<OrderModel> orderList=orderRepo.findAll();
        return orderList;
    }
    @Override
    public OrderModel getById(long id)
    {
        Optional<OrderModel> order=orderRepo.findById(id);
        if(order.isPresent())
        {
            OrderModel order1=orderRepo.findById(id).get();
            return order1;
        }
        else
        {
            throw new OrderException("Cannot find order id: "+id);
        }
    }
    @Override
    public String deleteById(long orderId, long userId)
    {
        Optional<OrderModel> order=orderRepo.findById(orderId);
        //Optional<UserModel> user = userRepo.findById(userId);
        Optional<UserModel> user = Optional.ofNullable(restTemp.getForObject("http://localhost:8081/homeuser/getById/1" + userId, UserModel.class));
        if(order.isPresent() && user.isPresent())
        {
            orderRepo.deleteById(orderId);
            emailSenderService.sendMail(user.get().getEmail(), "Order is deleted!","Hii...."+user.get().getFirstName()+" ! \n\n Your order has been deleted successfully! Order id: "+order.get().getOrderId());
            return "Details has been deleted!";
        }
        else
        {
            throw new OrderException("Cannot find order id: "+orderId);
        }
    }
    @Override
    public OrderModel updateById(long id, OrderDTO orderDTO)
    {
        //Optional<UserModel> user=userRepo.findById(orderDTO.getUser());
        Optional<UserModel> user = Optional.ofNullable(restTemp.getForObject("http://localhost:8081/homeuser/getById/1" + orderDTO.getUser(), UserModel.class));
        //List<BookModel> bookList=orderDTO.getBook().stream().map(book->bookRepo.findById(book).get()).collect(Collectors.toList());
        Optional<BookModel> book = Optional.ofNullable(restTemp.getForObject("http://localhost:8082/homebook/getById/1" + orderDTO.getBook(), BookModel.class));
        long price = orderDTO.getPrice() * orderDTO.getQuantity();
        if (user!=null || book!=null) {
            OrderModel order = new OrderModel(orderDTO.getUser(), orderDTO.getBook(), price, orderDTO.getQuantity(),orderDTO.getAddress(),orderDTO.getCancel());
            order.setOrderId(id);
            orderRepo.save(order);
            return order;
        }
        else
        {
            throw new OrderException("Please check & try again!");
        }
    }
}

