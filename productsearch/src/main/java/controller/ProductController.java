package com.klu.controller;

import com.klu.model.Product;
import com.klu.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repo;

    public ProductController(ProductRepository repo){
        this.repo = repo;
    }

    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@PathVariable String category){
        return repo.findByCategory(category);
    }

    @GetMapping("/expensive/{price}")
    public List<Product> expensive(@PathVariable double price){
        return repo.findByPriceGreaterThan(price);
    }
}