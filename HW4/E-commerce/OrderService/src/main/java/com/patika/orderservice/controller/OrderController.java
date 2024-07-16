package com.patika.orderservice.controller;


import com.patika.orderservice.client.InvoiceClient.response.InvoiceResponse;
import com.patika.orderservice.dto.request.OrderRequest;
import com.patika.orderservice.dto.response.OrderResponse;
import com.patika.orderservice.dtoRequest.InvoiceRequest;
import com.patika.orderservice.entities.Order;
import com.patika.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderResponse createOrder(@RequestBody OrderRequest request) {
       return orderService.createOrder(request);
    }


    @GetMapping
    public List<Order> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable long id) {
        return orderService.findById(id);
    }

    @PostMapping("/createInvoice")
    public ResponseEntity<InvoiceResponse> createInvoice(@RequestBody InvoiceRequest request) {
        return ResponseEntity.ok().body(orderService.createInvoice(request));
    }


}
