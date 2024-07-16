package com.patika.invoiceservice.dto.response;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class InvoiceResponse {

    private long invoiceId;
    private long customerId;
    private long orderId;
    private LocalDate createDate;
    private double totalAmount;




}
