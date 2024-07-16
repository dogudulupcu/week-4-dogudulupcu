package com.patika.kredinbizdeservice.entity;

import com.patika.kredinbizdeservice.enums.LoanType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "house_loans")
public class HouseLoan extends Loan {

    @Column(name = "loan_type", nullable = false)
    private final LoanType loanType = LoanType.KONUT_KREDISI;
    @Column(name = "city", nullable = false)
    private String city;

}
