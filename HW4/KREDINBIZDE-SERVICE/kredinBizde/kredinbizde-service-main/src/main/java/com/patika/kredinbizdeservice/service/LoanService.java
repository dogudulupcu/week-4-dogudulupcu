package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.enums.LoanType;
import com.patika.kredinbizdeservice.entity.Loan;
import com.patika.kredinbizdeservice.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository LoanRepository;

    public Loan save(Loan loan) {
        return LoanRepository.save(loan);
    }

    public List<Loan> getAll() {
        return LoanRepository.findAll();
    }

    public List<Loan> findByLoanType(LoanType loanType) {
        return LoanRepository.findByLoanType(loanType).orElse(null);
    }

    public Loan getById(long id) {
       return LoanRepository.findById(id).orElse(null);
    }

    public void delete(Loan loan) {
        LoanRepository.delete(loan);
    }

}
