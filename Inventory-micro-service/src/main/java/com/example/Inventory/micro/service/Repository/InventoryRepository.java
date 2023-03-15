package com.example.Inventory.micro.service.Repository;

import com.example.Inventory.micro.service.Models.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface InventoryRepository extends MongoRepository<Inventory,Integer> {
    @Query("{ 'product':?0}")
    Inventory findItemInfoInInventory(String product);

}
