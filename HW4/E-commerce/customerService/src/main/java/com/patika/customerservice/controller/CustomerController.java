package com.patika.customerservice.controller;

import com.patika.customerservice.client.OrderClient.response.OrderResponse;
import com.patika.customerservice.dtoRequest.OrderRequest;
import com.patika.customerservice.entities.Customer;
import com.patika.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public Customer create(@RequestBody Customer customer){
        return customerService.save(customer);
    }

    @GetMapping
    public List<Customer> getAll(){
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable long id){
        return ResponseEntity.ok().body(customerService.findById(id));
    }

    @PostMapping("/createOrder")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request){
        return ResponseEntity.ok().body(customerService.createOrder(request));
    }
}
