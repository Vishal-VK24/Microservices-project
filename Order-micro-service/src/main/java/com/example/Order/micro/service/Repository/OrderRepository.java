package com.example.Order.micro.service.Repository;

import com.example.Order.micro.service.Models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order,Integer> {
}
