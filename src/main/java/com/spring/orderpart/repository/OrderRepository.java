package com.spring.orderpart.repository;

import com.spring.orderpart.model.OrderModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel,Long>
{
//    @Transactional
//    @Modifying
//    @Query(value = "delete from orders.book_order where book_order.order_id=:orderId",nativeQuery = true)
//    void deleteById(Long orderId);
}
