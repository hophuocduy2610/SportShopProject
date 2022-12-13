package com.project.orderservice.service;

import com.project.orderservice.entity.Order;
import com.project.orderservice.entity.OrderDetail;
import com.project.orderservice.repository.OrderDetailRepo;
import com.project.orderservice.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderDetailRepo orderDetailRepo;

    @Override
    public void saveOrder(Order order) {
        orderRepo.save(order);
    }

    @Override
    public void saveOrderDetail(OrderDetail orderDetail) {
        orderDetailRepo.save(orderDetail);
    }

    @Override
    public List<Order> getOrderByCustomerID(int customerID) {
    	List<Order> listOrders = orderRepo.getOrderByCustomerId(customerID);
    	
        return listOrders;
    }

    @Override
    public List<OrderDetail> getOrderDetailByOrderID(int orderID) {
        List<OrderDetail> orderDetailList = orderDetailRepo.getOrderDetailByOrderId(orderID);
        return orderDetailList;
    }
}
