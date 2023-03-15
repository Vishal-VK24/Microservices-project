package com.example.Inventory.micro.service.Service;

import com.example.Inventory.micro.service.Models.Inventory;
import com.example.Inventory.micro.service.Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryService(InventoryRepository inventoryRepository){
        this.inventoryRepository=inventoryRepository;
    }
    public String addItemInInventory(Inventory inventory){
        inventoryRepository.save(inventory);
        return "product stocked in inventory";
    }
    public Inventory getItemInfoInInventoryByProduct(String product){
        Inventory inventory= inventoryRepository.findItemInfoInInventory(product);
        return inventory;
    }
    public Inventory getItemInfoInInventory(int id){
        Inventory inventory=inventoryRepository.findById(id).orElse(null);
        return inventory;
    }
    public void setStockForItem(int id,int stock){
        Inventory inventory=inventoryRepository.findById(id).orElse(null);
        inventory.setStock(inventory.getStock()+stock);
        inventoryRepository.save(inventory);
    }
    public Boolean checkStockForProducts(List<String> products){
        Boolean result=true;
        Inventory inventory;
        for(String i:products){
            inventory=inventoryRepository.findItemInfoInInventory(i);
            if(inventory.getStock()==0){
                result=false;
                return result;
            }
        }
        return result;
    }
    public void updateStockAfterOrder(List<String> products){
        List<Inventory> inventories=new ArrayList<>();
        Inventory inventory;
        for(String i:products){
            inventory=inventoryRepository.findItemInfoInInventory(i);
            inventory.setStock(inventory.getStock()-1);
            inventories.add(inventory);
        }
        inventoryRepository.saveAll(inventories);
    }
}
