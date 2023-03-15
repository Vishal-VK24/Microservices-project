package com.example.Product.micro.service.Repository;

import com.example.Product.micro.service.Models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product,Integer> {
    List<Product> findByCategory(String category);
    @Query("{price: {$lt:?1,$gt:?0}}")
    List<Product> findProductsByPrice(float start,float end);
}
