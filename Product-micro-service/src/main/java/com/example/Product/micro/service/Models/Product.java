package com.example.Product.micro.service.Models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Product {
    @Id
    @Generated
    private int id;
    private String name;
    private int price;
    private String category;
}
