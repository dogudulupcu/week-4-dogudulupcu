package com.patika.orderservice.service;


import com.patika.orderservice.client.InvoiceClient.InvoiceServiceClient;
import com.patika.orderservice.client.InvoiceClient.response.InvoiceResponse;
import com.patika.orderservice.converter.OrderConverter;
import com.patika.orderservice.client.InvoiceClient.request.InvoiceServiceRequest;
import com.patika.orderservice.dto.request.OrderRequest;
import com.patika.orderservice.dto.response.OrderResponse;
import com.patika.orderservice.dtoRequest.InvoiceRequest;
import com.patika.orderservice.entities.Order;
import com.patika.orderservice.producer.RequestProducer;
import com.patika.orderservice.producer.dto.InvoiceDTO;
import com.patika.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private  OrderRepository orderRepository = new OrderRepository();

    private final OrderConverter orderConverter;

    private final InvoiceServiceClient invoiceServiceClient;

    private final RequestProducer requestProducer;

    private static long orderId = 1;

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAll() {
        return orderRepository.getAll();
    }

    public Order findById(long orderId) {
        Optional<Order> foundOrder = orderRepository.findByOrderId(orderId);
        Order order = null;

        if (foundOrder.isPresent()) {
            order = foundOrder.get();
        }
        return order;
    }

    public OrderResponse createOrder(OrderRequest request) {
        Order order = orderConverter.toOrder(request, orderId);
        log.info("Order created: {}", order);
        orderId++;
        return orderConverter.toOrderResponse(orderRepository.save(order));
    }

    public InvoiceResponse createInvoice(InvoiceRequest request) {
        log.info("Order request received: {}", request.getOrder().getOrderId());
        Order order = findById(request.getOrder().getOrderId());
        log.info("Order found: {}", order);

       //Asenkron yapı Rabbite invoice oluşturmak için request gönderir.
       requestProducer.sendRequest(prepareInvoiceDTO(order));
     //  log.info("Invoice request sent: {}", prepareInvoiceDTO(order));

       //Senkron yapı Invoice oluşturur.
       //InvoiceResponse invoiceResponse = invoiceServiceClient.createInvoice(prepareRequest(order));


        return null;
    }

    private InvoiceDTO prepareInvoiceDTO(Order order) {
        return InvoiceDTO.builder()
               .order(order)
              .build();
    }

    private InvoiceServiceRequest prepareRequest(Order order) {
        InvoiceServiceRequest invoiceServiceRequest = new InvoiceServiceRequest();
        invoiceServiceRequest.setOrder(order);
        return invoiceServiceRequest;
    }
}
