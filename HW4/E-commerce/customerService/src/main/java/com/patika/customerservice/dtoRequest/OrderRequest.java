package com.patika.customerservice.dtoRequest;

import com.patika.customerservice.entities.Product;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


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
