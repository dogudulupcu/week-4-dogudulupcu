package com.patika.kredinbizdeservice.entity;


import com.patika.kredinbizdeservice.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;

    @OneToOne
    @JoinColumn(name = "credit_card_id")
    private CreditCard creditCard;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "amount", nullable =false)
    private BigDecimal amount;

    @Column(name = "create_date", nullable = false)
    private LocalDate createDate;

    @Column(name = "application_status", nullable = false)
    private ApplicationStatus applicationStatus;



}
