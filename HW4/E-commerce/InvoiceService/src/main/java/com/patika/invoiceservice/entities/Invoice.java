package com.patika.invoiceservice.entities;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Invoice {
    private long invoiceId;
    private long customerId;
    private Order order;
    private double totalAmount;
    private LocalDate createDate;
}
