package com.patika.orderservice.dtoRequest;

import com.patika.orderservice.entities.Order;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class InvoiceRequest {
    private Order order;
}
