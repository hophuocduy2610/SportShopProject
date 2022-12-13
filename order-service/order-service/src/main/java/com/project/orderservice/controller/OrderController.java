package com.project.orderservice.controller;

import com.project.inventoryservice.entity.Inventory;
import com.project.orderservice.entity.Order;
import com.project.orderservice.entity.OrderDetail;
import com.project.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private OrderService orderService;

    private OrderDetail orderDetailTemp = new OrderDetail();
    private int orderID;

    //Pay the order for the payment page
    @PostMapping("/payment")
    public String payMent(@RequestBody Order order) {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        orderDetailList = order.getOrderDetailList().stream().toList();

        Order orderTemp = new Order(order.getId(), order.getCustomerID(),
                order.getOrderDate(), order.getTotalPrice());
        System.out.println(orderTemp.getCustomerID() + " " + orderTemp.getTotalPrice());
        orderService.saveOrder(orderTemp);
        orderID = order.getId();

        for (OrderDetail orderDetail : orderDetailList) {
            orderDetailTemp = new OrderDetail(orderDetail.getId(), orderID, orderDetail.getSkuCode(), orderDetail.getProductName(),
                    orderDetail.getPrice(), orderDetail.getQuantity());
            orderService.saveOrderDetail(orderDetailTemp);
            Inventory inventory = new Inventory();
            inventory.setQuantity(orderDetail.getQuantity());
            inventory.setSkuCodeProduct(orderDetail.getSkuCode());
            final String url = "http://localhost:8888/inventory/updateproductquantity";
            restTemplate.put(url, inventory);
        }
        return "Payment success";
    }
    
    //Get order by customer id for purchase history page
    @GetMapping("/getorderbyid/{customerId}")
    public List<Order> getOrderByCustomer(@PathVariable int customerId) {
        return orderService.getOrderByCustomerID(customerId);
    }

    //Get invoice details by customer id for purchase history page
    @GetMapping("/getorderdetailbyid/{orderId}")
    public List<OrderDetail> getOrderDetailByOrderId(@PathVariable int orderId) {
        return orderService.getOrderDetailByOrderID(orderId);
    }
}
