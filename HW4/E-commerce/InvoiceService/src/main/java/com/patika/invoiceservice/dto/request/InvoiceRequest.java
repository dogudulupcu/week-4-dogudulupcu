package com.patika.invoiceservice.dto.request;

import com.patika.invoiceservice.entities.Order;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class InvoiceRequest {
    private Order order;
}
