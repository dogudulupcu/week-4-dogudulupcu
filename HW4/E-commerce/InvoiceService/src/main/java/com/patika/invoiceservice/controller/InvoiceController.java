package com.patika.invoiceservice.controller;

import com.patika.invoiceservice.dto.request.InvoiceRequest;
import com.patika.invoiceservice.dto.response.InvoiceResponse;
import com.patika.invoiceservice.entities.Invoice;
import com.patika.invoiceservice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping
    public InvoiceResponse createInvoice(@RequestBody InvoiceRequest request) {
        return invoiceService.createInvoice(request);
    }

    @GetMapping
    public List<Invoice> getAll() {
        return invoiceService.getAll();
    }

    @GetMapping("/{id}")
    public Invoice getInvoiceById(@PathVariable long id) {
        return invoiceService.findById(id);
    }


}
