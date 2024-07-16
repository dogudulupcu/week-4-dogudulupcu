package com.patika.customerservice.client.OrderClient.response;


import com.patika.customerservice.client.OrderClient.enums.OrderStatus;
import com.patika.customerservice.entities.Product;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderResponse {
    private long orderId;
    private long customerId;
    private Product product;
    private LocalDate createDate;
    private OrderStatus orderStatus;

}
