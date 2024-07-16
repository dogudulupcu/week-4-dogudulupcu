package com.patika.garantibankservice.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApplicationRequest {
    private long userId;
    private long loanId;
}
