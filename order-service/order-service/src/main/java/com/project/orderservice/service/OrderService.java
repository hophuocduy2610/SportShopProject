package com.project.orderservice.service;

import com.project.orderservice.entity.Order;
import com.project.orderservice.entity.OrderDetail;

import java.util.List;

public interface OrderService {
    public void saveOrder(Order order);

    public void saveOrderDetail(OrderDetail orderDetail);

    public List<Order> getOrderByCustomerID(int customerID);

    public List<OrderDetail> getOrderDetailByOrderID(int orderID);
}
