package com.patika.invoiceservice.repository;

import com.patika.invoiceservice.entities.Invoice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InvoiceRepository {

    private List<Invoice> invoiceList= new ArrayList<>();

    public List<Invoice> getAll(){
        return invoiceList;
    }

    public Invoice save(Invoice invoice){
        invoiceList.add(invoice);
        return invoice;
    }

    public Optional<Invoice> findById(long invoiceId){
        return invoiceList.stream()
                .filter(invoice -> invoice.getInvoiceId() == invoiceId)
                .findFirst();
    }



}
