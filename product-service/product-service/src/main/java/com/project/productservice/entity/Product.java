package com.project.productservice.entity;

import lombok.*;

import javax.persistence.*;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String productName;
	private String skuCodeProduct;
	private String description;
	private Double price;
	private String image;

	public Product() {
	}

	public Product(int id, String productName, String skuCodeProduct, String description, Double price, String image) {
		super();
		this.id = id;
		this.productName = productName;
		this.skuCodeProduct = skuCodeProduct;
		this.description = description;
		this.price = price;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSkuCodeProduct() {
		return skuCodeProduct;
	}

	public void setSkuCodeProduct(String skuCodeProduct) {
		this.skuCodeProduct = skuCodeProduct;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
