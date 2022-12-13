package com.project.inventoryservice.service;

import com.project.inventoryservice.entity.Inventory;

public interface InventoryService {

    public Inventory getInventoryOfProductBySkuCode(String skucode);
    public boolean updateProductQuantityBySkuCode(int quantity, String skuCode);
    
}
