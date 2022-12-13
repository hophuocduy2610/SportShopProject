package com.project.inventoryservice.repository;

import com.project.inventoryservice.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface InventoryRepo extends JpaRepository<Inventory, Integer> {
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE inventory i SET i.quantity =:quantity WHERE i.sku_code_product =:skucode", nativeQuery = true)
    public void updateProductQuantityBySkuCode(@Param("quantity") int quantity,@Param("skucode") String skuCode);


    @Query(value = "SELECT i.quantity FROM inventory i WHERE i.sku_code_product = ?", nativeQuery = true)
    public int getQuantityBySkuCode(String skuCode);
    @Query(value = "SELECT * FROM inventory i WHERE i.sku_code_product = ?", nativeQuery = true)
    public Inventory getInventoryOfProductBySkuCode(String skuCode);
}
