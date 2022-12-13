package com.project.inventoryservice.controller;

import com.project.inventoryservice.entity.Inventory;
import com.project.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
	@Autowired
	private InventoryService inventoryService;

	Random random = new Random(-6732303926L);

	// Get the inventory quantity of the product
	@GetMapping("/getinventoryofproduct/{skucode}")
	public Inventory getInventoryOfProduct(@PathVariable String skucode) {
//		try {
//			Thread.sleep(2 * 1000);
//		} catch (InterruptedException interruptedException) {
//			interruptedException.printStackTrace();
//		}
		return inventoryService.getInventoryOfProductBySkuCode(skucode);
	}

	// Update product inventory after payment of invoice
	@PutMapping("/updateproductquantity")
	public Boolean updateQuantityProduct(@RequestBody Inventory inventory) {
		String skuCode = inventory.getSkuCodeProduct();
		return inventoryService.updateProductQuantityBySkuCode(inventory.getQuantity(), skuCode);
	}
}
