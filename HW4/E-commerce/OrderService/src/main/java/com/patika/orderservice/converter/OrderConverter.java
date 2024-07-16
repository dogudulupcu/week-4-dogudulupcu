package com.patika.orderservice.converter;

import com.patika.orderservice.dto.request.OrderRequest;

import com.patika.orderservice.dto.response.OrderResponse;
import com.patika.orderservice.dto.response.OrderStatus;
import com.patika.orderservice.entities.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class OrderConverter {

    public Order toOrder(OrderRequest request, long orderId){
        return Order.builder()
                .customerId(request.getCustomerId())
                .product(request.getProduct())
                .createDate(LocalDate.now())
                .orderId(orderId)
                .orderStatus(OrderStatus.INITIAL)
                .build();


    }

    public OrderResponse toOrderResponse(Order order){
        return OrderResponse.builder()
                .orderId(order.getOrderId())
                .customerId(order.getCustomerId())
                .createDate(order.getCreateDate())
                .orderStatus(order.getOrderStatus())
                .product(order.getProduct())
                .build();


    }


}
