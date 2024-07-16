package com.patika.kredinbizdeservice.entity;


import com.patika.kredinbizdeservice.enums.LoanType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "consumer_loans")
public class ConsumerLoan extends Loan {

    @Column(name = "loanType", nullable = false)
    private final LoanType loanType = LoanType.IHTIYAC_KREDISI;


    @ElementCollection
    @CollectionTable(name = "installment_options", joinColumns = @JoinColumn(name = "consumer_loan_id"))
    @Column(name = "installment_options", nullable = false)
    private List<Integer> installmentOptions;

}
