package com.patika.kredinbizdeservice.entity.dto;


import com.patika.kredinbizdeservice.enums.ApplicationStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationDTO {
    private Long id;
    private Long loanId;
    private Long creditCardId;
    private Long userId;
    private BigDecimal amount;
    private LocalDate createDate;
    private ApplicationStatus applicationStatus;
}
