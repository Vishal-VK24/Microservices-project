package com.example.Order.micro.service.Controller;

import com.example.Order.micro.service.Models.Order;
import com.example.Order.micro.service.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<String> addOrder(@RequestBody Order order){

        return orderService.addOrder(order);
    }
    @GetMapping("/orders")
    public ResponseEntity<Order> getOrder(@RequestParam int id){
        Order order=orderService.getOrder(id);
        if(order==null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(order);
        else
            return ResponseEntity.status(HttpStatus.OK).body(order);
    }
}
