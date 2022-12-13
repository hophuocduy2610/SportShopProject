package com.project.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@IdClass(OrderDetailPK.class)
@Table(name = "orderdetail")
public class OrderDetail implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -902211824932110418L;

	@Id
    private Integer id;

    @Id
    @Column(name = "orderId")
    private int ordId;

    private String skuCode;
    private String productName;
    private Double price;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "orderId", insertable = false, updatable = false)
    @JsonBackReference
    private Order orderID;

    public OrderDetail() {
	}

	public OrderDetail(int id, int ordId, String skuCode, String productName, Double price, int quantity) {
        this.id = id;
        this.ordId = ordId;
        this.skuCode = skuCode;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getOrdId() {
		return ordId;
	}

	public void setOrdId(int ordId) {
		this.ordId = ordId;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Order getOrderID() {
		return orderID;
	}

	public void setOrderID(Order orderID) {
		this.orderID = orderID;
	}
}
