package com.patika.kredinbizdeservice.client.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ApplicationResponse {
    private long userId;
    private long loanId;
    private LocalDate createDate;
    private ApplicationStatus applicationStatus;
}
