package com.patika.customerservice.repository;

import com.patika.customerservice.entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository {

    private List<Product> productList = new ArrayList<>();

    public void save(Product product){
        productList.add(product);
    }

    public List<Product> getAll(){
        return productList;
    }

    public Optional<Product> findById(long productId){
        return productList.stream()
                .filter(product -> product.getProductId() == productId)
                .findFirst();

    }
}
