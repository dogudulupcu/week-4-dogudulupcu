package com.patika.garantibankservice.entity;

import com.patika.garantibankservice.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "loan_id", nullable = false)
    private long loanId;

    @Column(name = "create_date", nullable = false)
    private LocalDate createDate;

    @Column(name = "application_status", nullable = false)
    private ApplicationStatus applicationStatus;


}
