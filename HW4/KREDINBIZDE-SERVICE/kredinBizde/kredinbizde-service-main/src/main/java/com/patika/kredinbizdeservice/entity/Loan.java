package com.patika.kredinbizdeservice.entity;

import com.patika.kredinbizdeservice.enums.LoanType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loans")
@Inheritance(strategy = InheritanceType.JOINED)
public  class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "installment", nullable = false)
    private Integer installment;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @Column(name = "interest_rate", nullable = false)
    private Double interestRate;

    @Column(name = "loan_type", nullable = false)
    private LoanType loanType;

    @OneToOne(mappedBy = "loan")
    private Application application;


}
