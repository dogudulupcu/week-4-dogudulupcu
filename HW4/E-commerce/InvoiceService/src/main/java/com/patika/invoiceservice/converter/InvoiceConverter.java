package com.patika.invoiceservice.converter;


import com.patika.invoiceservice.dto.request.InvoiceRequest;
import com.patika.invoiceservice.dto.response.InvoiceResponse;
import com.patika.invoiceservice.entities.Invoice;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class InvoiceConverter {


    public Invoice toInvoice(InvoiceRequest request, long invoiceId) {
        return Invoice.builder()
                .invoiceId(invoiceId)
                .order(request.getOrder())
                .customerId(request.getOrder().getCustomerId())
                .totalAmount(request.getOrder().getProduct().getProductPrice())
                .createDate(LocalDate.now())
                .build();

    }

    public InvoiceResponse toInvoiceResponse(Invoice save) {
        return InvoiceResponse.builder()
                .invoiceId(save.getInvoiceId())
                .customerId(save.getCustomerId())
                .orderId(save.getOrder().getOrderId())
                .createDate(save.getCreateDate())
                .totalAmount(save.getTotalAmount())
                .build();
    }
}
