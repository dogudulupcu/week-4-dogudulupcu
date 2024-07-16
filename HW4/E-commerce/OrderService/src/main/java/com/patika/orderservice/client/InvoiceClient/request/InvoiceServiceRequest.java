package com.patika.orderservice.client.InvoiceClient.request;

import com.patika.orderservice.entities.Order;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InvoiceServiceRequest {
    private Order order;
}
