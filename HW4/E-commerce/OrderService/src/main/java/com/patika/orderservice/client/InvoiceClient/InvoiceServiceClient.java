package com.patika.orderservice.client.InvoiceClient;


import com.patika.orderservice.client.InvoiceClient.request.InvoiceServiceRequest;
import com.patika.orderservice.client.InvoiceClient.response.InvoiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "invoiceService", url = "localhost:8084")
public interface InvoiceServiceClient {

    @PostMapping("api/v1/invoices")
    InvoiceResponse createInvoice(@RequestBody InvoiceServiceRequest request);


}
