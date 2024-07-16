package com.patika.orderservice.repository;

import com.patika.orderservice.entities.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepository {

    private List<Order> orderList=new ArrayList<>();

    public Order save(Order order){
        orderList.add(order);
        return order;
    }

    public List<Order> getAll(){
        return orderList;
    }

public Optional<Order> findByOrderId(long orderId){
        return orderList.stream()
                .filter(order -> order.getOrderId() == orderId)
                .findFirst();
    }


}
