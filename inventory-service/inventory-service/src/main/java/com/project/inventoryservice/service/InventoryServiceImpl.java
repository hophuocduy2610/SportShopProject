package com.project.inventoryservice.service;

import com.project.inventoryservice.entity.Inventory;
import com.project.inventoryservice.repository.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@CacheConfig(cacheNames = {"Inventory"})
public class InventoryServiceImpl implements InventoryService{

    @Autowired
    private InventoryRepo inventoryRepo;
    
    @Override
    @Cacheable(key = "#skucode")
    public Inventory getInventoryOfProductBySkuCode(String skucode) {
        return inventoryRepo.getInventoryOfProductBySkuCode(skucode);
    }
    @Override
    public boolean updateProductQuantityBySkuCode(int quantity, String skuCode) {
        try {
            int quantityInventory = inventoryRepo.getQuantityBySkuCode(skuCode);
            int quantityTemp = quantityInventory - quantity;
            inventoryRepo.updateProductQuantityBySkuCode(quantityTemp, skuCode);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


}
