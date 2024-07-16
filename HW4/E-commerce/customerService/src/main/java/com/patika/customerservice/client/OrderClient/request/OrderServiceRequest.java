package com.patika.customerservice.client.OrderClient.request;


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
public class OrderServiceRequest {
    private long customerId;
    private Product product;
}
