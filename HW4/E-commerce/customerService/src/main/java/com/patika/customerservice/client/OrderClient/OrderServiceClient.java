package com.patika.customerservice.client.OrderClient;


import com.patika.customerservice.client.OrderClient.request.OrderServiceRequest;
import com.patika.customerservice.client.OrderClient.response.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "orderService", url = "localhost:8083")
public interface OrderServiceClient {

    @PostMapping("api/v1/orders")
    OrderResponse createOrder(@RequestBody OrderServiceRequest request);

}
