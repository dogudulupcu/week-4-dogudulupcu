package com.patika.kredinbizdeservice.repository;

import com.patika.kredinbizdeservice.enums.LoanType;
import com.patika.kredinbizdeservice.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface LoanRepository extends JpaRepository<Loan, Long>{

     Optional<List<Loan>> findByLoanType(LoanType loanType);


}
