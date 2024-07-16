package com.patika.orderservice.dto.response;

import com.patika.orderservice.entities.Product;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderResponse {
    private long orderId;
    private long customerId;
    private Product product;
    private LocalDate createDate;
    private OrderStatus orderStatus;
}
