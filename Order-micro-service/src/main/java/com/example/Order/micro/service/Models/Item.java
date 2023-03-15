package com.example.Order.micro.service.Models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Item {
    @Id
    private int id;
    private String name;
    private float price;
}
