package com.patika.customerservice.controller;

import com.patika.customerservice.entities.Product;
import com.patika.customerservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productService.save(product);
    }

    @GetMapping
    public List<Product> getAll(){
        return productService.getAll();
    }

@GetMapping("/{id}")
    public Product getProductById(@PathVariable long id){
        return productService.findById(id);
    }
}
