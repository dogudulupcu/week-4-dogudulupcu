package com.patika.customerservice.service;

import com.patika.customerservice.client.OrderClient.OrderServiceClient;
import com.patika.customerservice.client.OrderClient.request.OrderServiceRequest;
import com.patika.customerservice.client.OrderClient.response.OrderResponse;
import com.patika.customerservice.dtoRequest.OrderRequest;
import com.patika.customerservice.entities.Customer;
import com.patika.customerservice.entities.Product;
import com.patika.customerservice.exceptions.CustomerServiceException;
import com.patika.customerservice.exceptions.ExceptionMessages;
import com.patika.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private CustomerRepository customerRepository=new CustomerRepository();
    private final ProductService productService;
    private final OrderServiceClient orderServiceClient;


    public Customer save(Customer customer) {
        customerRepository.save(customer);
        return customer;
    }

    public List<Customer> getAll() {
        return customerRepository.getAll();
    }

    public Customer findById(long id) {
        Optional<Customer> foundCustomer = customerRepository.findById(id);
        Customer customer = foundCustomer.orElseThrow(()-> new CustomerServiceException(ExceptionMessages.CUSTOMER_NOT_FOUND));

        if (foundCustomer.isPresent()) {
            customer = foundCustomer.get();
        }
        return customer;
    }

    public OrderResponse createOrder(OrderRequest request) {
        Customer customer = findById(request.getCustomerId());
        log.info("Customer found: {}", customer);
        Product product = productService.findById(request.getProduct().getProductId());
        log.info("Product found: {}", product);

        OrderResponse applicationResponse = orderServiceClient.createOrder(prepareRequest(customer, product));


        customer.addOrder(applicationResponse.getOrderId());

        return applicationResponse;

    }

    private OrderServiceRequest prepareRequest(Customer customer, Product product) {
    OrderServiceRequest orderServiceRequest = new OrderServiceRequest();
    orderServiceRequest.setCustomerId(customer.getCustomerId());
    orderServiceRequest.setProduct(product);
    return orderServiceRequest;
    }
}
