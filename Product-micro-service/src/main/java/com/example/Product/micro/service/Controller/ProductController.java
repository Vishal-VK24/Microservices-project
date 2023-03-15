package com.example.Product.micro.service.Controller;

import com.example.Product.micro.service.Models.Product;
import com.example.Product.micro.service.Service.ProductService;
import com.example.Product.micro.service.dto.ProductRequest;
import com.example.Product.micro.service.dto.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody ProductRequest productRequest){
        productService.addProduct(productRequest);
    }
    @GetMapping("/products")
    public List<Product> getAllProducts(){
            return productService.getAllProducts();
    }
    @GetMapping("/products/category")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam String category){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductsByCategory(category));
    }
    @GetMapping("/products/price")
    public ResponseEntity<List<Product>> getProductsByPrice(@RequestParam(required = false) Float start,@RequestParam(required = false) Float end){
        if(start!=null&&end!=null)
            return ResponseEntity.status(HttpStatus.OK).body(productService.getProductsByPrice(start,end));
        else if(start==null&&end==null)
            return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
        else if(start==null)
            return ResponseEntity.status(HttpStatus.OK).body(productService.getProductsByPrice(0,end));
        else
            return ResponseEntity.status(HttpStatus.OK).body(productService.getProductsByPrice(start,0x1.fffffeP+127f));
    }

}
