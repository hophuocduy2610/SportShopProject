package com.project.orderservice.repository;

import com.project.orderservice.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepo extends JpaRepository<OrderDetail, Integer> {
    @Query(value = "SELECT * FROM orderdetail o WHERE o.order_id = ?", nativeQuery = true)
    public List<OrderDetail> getOrderDetailByOrderId(int orderID);
}
