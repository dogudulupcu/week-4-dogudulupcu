package com.patika.customerservice.exceptions;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionMessages {

       public static String CUSTOMER_NOT_FOUND = "Customer not found";

         public static String PRODUCT_NOT_FOUND = "Product not found";



}
