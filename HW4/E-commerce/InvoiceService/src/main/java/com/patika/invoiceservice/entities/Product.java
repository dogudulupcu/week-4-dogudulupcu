package com.patika.invoiceservice.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Product {

    private long productId;

    private String productName;

    private double productPrice;

    private int productStock;

}
