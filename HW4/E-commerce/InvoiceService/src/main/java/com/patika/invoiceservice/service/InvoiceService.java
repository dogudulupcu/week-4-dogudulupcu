package com.patika.invoiceservice.service;

import com.patika.invoiceservice.converter.InvoiceConverter;
import com.patika.invoiceservice.dto.request.InvoiceRequest;
import com.patika.invoiceservice.dto.response.InvoiceResponse;
import com.patika.invoiceservice.entities.Invoice;
import com.patika.invoiceservice.listener.RequestListener;
import com.patika.invoiceservice.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class InvoiceService {

    private InvoiceRepository invoiceRepository = new InvoiceRepository();

    private final InvoiceConverter invoiceConverter;


    private static long invoiceId = 1;


    //save
    public Invoice save(Invoice invoice){
        return invoiceRepository.save(invoice);
    }

    //getAll
    public List<Invoice> getAll(){
        return invoiceRepository.getAll();
    }

    //findById
    public Invoice findById(long invoiceId){
        Optional<Invoice> foundInvoice = invoiceRepository.findById(invoiceId);
        Invoice invoice = null;

        if (foundInvoice.isPresent()) {
            invoice = foundInvoice.get();
        }
        return invoice;
    }

    public InvoiceResponse createInvoice(InvoiceRequest request) {
        Invoice invoice = invoiceConverter.toInvoice(request, invoiceId);

        log.info("Invoice created: {}", invoice);
        invoiceId++;
        return invoiceConverter.toInvoiceResponse(invoiceRepository.save(invoice));

    }




}
