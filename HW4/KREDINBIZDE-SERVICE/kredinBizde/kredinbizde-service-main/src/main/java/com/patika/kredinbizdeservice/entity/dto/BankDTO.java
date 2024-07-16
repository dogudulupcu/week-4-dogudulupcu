package com.patika.kredinbizdeservice.entity.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Builder
public class BankDTO {

    private Long id;

    private String name;

    private double interestRate;

    private List<CreditCardDTO> creditCard;


}
