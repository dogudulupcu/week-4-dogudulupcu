package com.patika.customerservice.service;


import com.patika.customerservice.entities.Product;
import com.patika.customerservice.exceptions.CustomerServiceException;
import com.patika.customerservice.exceptions.ExceptionMessages;
import com.patika.customerservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository = new ProductRepository();

    public Product save(Product product) {
        productRepository.save(product);
        return product;
    }

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public Product findById(long productId) {
        Optional<Product> foundProduct = productRepository.findById(productId);
        Product product = foundProduct.orElseThrow(()-> new CustomerServiceException(ExceptionMessages.PRODUCT_NOT_FOUND));

        if (foundProduct.isPresent()) {
            product = foundProduct.get();
        }
        return product;
    }
}
