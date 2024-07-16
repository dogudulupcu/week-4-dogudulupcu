package com.patika.orderservice.client.InvoiceClient.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InvoiceResponse {
    private long invoiceId;
    private long customerId;
    private long orderId;
    private LocalDate createDate;
    private double totalAmount;


}
