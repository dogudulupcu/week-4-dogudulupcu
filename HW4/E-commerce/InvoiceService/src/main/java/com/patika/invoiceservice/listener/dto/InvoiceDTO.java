package com.patika.invoiceservice.listener.dto;

import com.patika.invoiceservice.entities.Order;
import lombok.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class InvoiceDTO {
    private Order order;

}
