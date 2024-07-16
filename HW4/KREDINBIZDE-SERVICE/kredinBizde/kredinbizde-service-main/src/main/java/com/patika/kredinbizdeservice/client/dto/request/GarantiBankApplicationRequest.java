package com.patika.kredinbizdeservice.client.dto.request;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GarantiBankApplicationRequest {
    private long userId;
    private long loanId;
}
