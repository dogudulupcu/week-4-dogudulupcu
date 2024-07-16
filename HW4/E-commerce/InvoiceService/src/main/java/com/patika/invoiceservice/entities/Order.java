package com.patika.invoiceservice.entities;

import com.patika.invoiceservice.enums.OrderStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Order {

    private long orderId;
    private long customerId;
    private Product product;
    private LocalDate createDate;
    private OrderStatus orderStatus;
}
