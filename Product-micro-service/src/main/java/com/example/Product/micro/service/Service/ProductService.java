package com.example.Product.micro.service.Service;

import com.example.Product.micro.service.Repository.ProductRepository;
import com.example.Product.micro.service.Models.Product;
import com.example.Product.micro.service.dto.ProductRequest;
import com.example.Product.micro.service.dto.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void addProduct(ProductRequest productRequest)
    {
        double id = Math.random()*100000;
        while(productRepository.findById((int)id).orElse(null)!=null)
            id = Math.random()*100000;
        Product product= Product.builder().
            id((int)id).
            name(productRequest.getName())
            .price(productRequest.getPrice()).
        category(productRequest.getCategory()).build();
        productRepository.save(product);
    }
    public Product getProduct(int Id){

        return productRepository.findById(Id).orElse(null);
    }
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategory(String category) {
        List<Product> products = productRepository.findAll();
        return products;
    }


    public List<Product> getProductsByPrice(float start, float end) {
        return productRepository.findProductsByPrice(start,end);
    }
}
