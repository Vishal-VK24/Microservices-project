package com.example.Order.micro.service.Service;

import com.example.Order.micro.service.Models.Item;
import com.example.Order.micro.service.Models.Order;
import com.example.Order.micro.service.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public ResponseEntity<String> addOrder(Order order){
        double id = Math.random()*100000;
        while(orderRepository.findById((int)id).orElse(null)!=null)
            id = Math.random()*100000;
        order.setId((int)id);
        List<String> products=order.getOrderItems().stream().map(Item::getName).collect(Collectors.toList());
        Boolean result= webClientBuilder.build().get()
                .uri("http://INVENTORY-SERVICE/inventory/products",uriBuilder -> uriBuilder.queryParam("products",products).build())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        System.out.println("hii");
        if(result==true) {
            orderRepository.save(order);
            webClientBuilder.build().post()
                    .uri("http://INVENTORY-SERVICE/inventory/order-placed",uriBuilder -> uriBuilder.queryParam("products",products).build())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            return ResponseEntity.status(HttpStatus.OK).body(order.toString());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("stock unavailable");
        }
        }
    public Order getOrder(int Id){
        return orderRepository.findById(Id).orElse(null);
    }
}
