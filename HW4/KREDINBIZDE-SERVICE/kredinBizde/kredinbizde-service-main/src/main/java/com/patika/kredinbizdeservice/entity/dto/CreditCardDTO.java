package com.patika.kredinbizdeservice.entity.dto;

import com.patika.kredinbizdeservice.entity.CreditCard;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditCardDTO {
    private Long id;
    private String name;
    private String fee;
    private String bankId;
    private String bankName;
    private String applicationId;


    public static CreditCardDTO creditCardToStringDTO(CreditCard creditCard) {
            return CreditCardDTO.builder()
                    .id(creditCard.getId())
                    .name(creditCard.getName())
                    .fee(creditCard.getFee().toString())
                    .bankId(creditCard.getBank().getId().toString())
                    .bankName(creditCard.getBank().getName())
                    .build();
    }

    public static List<CreditCardDTO> creditCardToStringDTOList(List<CreditCard> creditCardList) {
        return creditCardList.stream().map(CreditCardDTO::creditCardToStringDTO).toList();
    }
}
