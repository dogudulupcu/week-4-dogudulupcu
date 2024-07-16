package com.patika.orderservice.entities;

import com.patika.orderservice.dto.response.OrderStatus;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Order {

    private long orderId;
    private long customerId;
    private Product product;
    private LocalDate createDate;
    private OrderStatus orderStatus;

}
