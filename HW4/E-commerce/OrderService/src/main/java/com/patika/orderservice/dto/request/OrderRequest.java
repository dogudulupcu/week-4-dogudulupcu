package com.patika.orderservice.dto.request;

import com.patika.orderservice.entities.Product;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderRequest {
    private long customerId;
    private Product product;
}
