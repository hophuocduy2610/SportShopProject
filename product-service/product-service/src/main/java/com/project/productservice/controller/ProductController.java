package com.project.productservice.controller;

import com.project.inventoryservice.entity.Inventory;
import com.project.productservice.entity.Product;
import com.project.productservice.repository.ProductRepo;
import com.project.productservice.service.ProductService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/product")
public class ProductController {

	RestTemplate restTemplate = new RestTemplate();
	@Autowired
	private ProductService productService;

	@GetMapping("/getall")
	public List<Product> getAllProduct() {
		return productService.getAllProduct();
	}

	private static final String NAME_CIRCUIT = "inventory";
	int methodCallTimes = 1;

	// Get all product with quantity > 0 for purchase page
	@GetMapping("/getproductsbyquantity")
//    @CircuitBreaker(name = NAME_CIRCUIT, fallbackMethod = "getAvailableProductsFallback")
	@Retry(name = NAME_CIRCUIT)
//    @TimeLimiter(name = NAME_CIRCUIT, fallbackMethod = "getAvailableProductsTimeLimiterFallback")
	public List<Product> getProductsWithQuantityGreaterThanZero() {
		System.out.println("Retry method called " + methodCallTimes++ + " times at " + new Date());
		List<Product> products = productService.getAllProduct();
		List<Product> productList = new ArrayList<>();
		for (Product product : products) {
			final String url = "http://localhost:8888/inventory/getinventoryofproduct/" + product.getSkuCodeProduct();
			Inventory inventory = restTemplate.getForObject(url, Inventory.class);
			int quantity = inventory.getQuantity();
			if (quantity > 0) {
				productList.add(product);
			}
		}
//        return CompletableFuture.completedFuture(productList);
		return productList;
	}

	// Fallback method for CircuitBreaker
	public List<Product> getAvailableProductsFallback(Exception ex) {
		System.out.println("Circuit Breaker open");
		return Stream.of(new Product(0, "", "", "", 0.0, "")).toList();
	}

	// Fallback method for Timelimiter
	public CompletableFuture<List<Product>> getAvailableProductsTimeLimiterFallback(Exception ex) {
		System.out.println("Service is timed out");
		List<Product> products = new ArrayList<>();
		products.add(new Product(0, "", "", "", 0.0, ""));
		return CompletableFuture.completedFuture(products);
	}

	// Get product by product id for product detail page
	@GetMapping("/getproductbyid/{id}")
	public Product getProductByID(@PathVariable int id) {
		return productService.getProductById(id);
	}
}
