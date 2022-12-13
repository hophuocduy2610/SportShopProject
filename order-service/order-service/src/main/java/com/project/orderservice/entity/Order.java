package com.project.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@Table(name = "orders")
public class Order implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8688034842742737262L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private int id;
    private int customerID;
    private String orderDate;
    private Double totalPrice;

    @OneToMany(mappedBy = "orderID")
    @JsonManagedReference
    List<OrderDetail> orderDetailList;

    public Order() {
	}

	public Order(int id, int customerID, String orderDate, Double totalPrice) {
        this.id = id;
        this.customerID = customerID;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    public Order(int id) {
        this.id = id;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}
}
