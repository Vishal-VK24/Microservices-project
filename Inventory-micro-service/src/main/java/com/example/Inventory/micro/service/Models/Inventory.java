package com.example.Inventory.micro.service.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Inventory {
    @Id
    private int id;
    private String product;
    private int stock;
}
