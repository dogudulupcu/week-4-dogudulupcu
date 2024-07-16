package com.patika.customerservice.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Customer {

    private long customerId;

    private String customerEmail;

    private String customerName;

    private List<Long> orders=new ArrayList<>();



    public void addOrder(long orderId){
        orders.add(orderId);
    }
}
