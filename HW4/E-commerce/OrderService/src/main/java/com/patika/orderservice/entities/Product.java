package com.patika.orderservice.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Product {

    private long productId;

    private String productName;

    private double productPrice;

    private int productStock;


}