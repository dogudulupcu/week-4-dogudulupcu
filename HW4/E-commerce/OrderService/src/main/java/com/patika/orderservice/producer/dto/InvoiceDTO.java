package com.patika.orderservice.producer.dto;


import com.patika.orderservice.entities.Order;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class InvoiceDTO {
    private Order order;
   // private String order;

}
