package com.example.Inventory.micro.service.Controller;

import com.example.Inventory.micro.service.Models.Inventory;
import com.example.Inventory.micro.service.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private  InventoryController(InventoryService inventoryService){
        this.inventoryService=inventoryService;
    }
    @PostMapping("/inventory")
    public ResponseEntity<String> addItemInInventory(@RequestBody Inventory inventory){
        inventoryService.addItemInInventory(inventory);
        return ResponseEntity.status(HttpStatus.CREATED).body("Item created in DB");
    }
    @GetMapping("/inventory")
    public ResponseEntity<Inventory> getItemInInventory(@RequestParam String product){
        Inventory inventory = inventoryService.getItemInfoInInventoryByProduct(product);
        if(inventory==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        else
            return ResponseEntity.status(HttpStatus.OK).body(inventory);
    }
    @PatchMapping("/inventory")
    public ResponseEntity<String> setStockForItem(@RequestParam int id,@RequestParam int stock){
        Inventory inventory = inventoryService.getItemInfoInInventory(id);
        if(inventory==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such item");
        else {
            inventoryService.setStockForItem(id,stock);
            return ResponseEntity.status(HttpStatus.OK).body("stock updated");
        }
    }
    @GetMapping("/inventory/products")
    public Boolean getStockInfoForProducts(@RequestParam List<String> products){
        return inventoryService.checkStockForProducts(products);
    }
    @PostMapping("/inventory/order-placed")
    public String updateStockAfterOrder(@RequestParam List<String> products){
        inventoryService.updateStockAfterOrder(products);
        return "sucess";
    }
}
