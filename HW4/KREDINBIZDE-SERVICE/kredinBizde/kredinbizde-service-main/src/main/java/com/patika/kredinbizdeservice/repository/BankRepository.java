package com.patika.kredinbizdeservice.repository;

import com.patika.kredinbizdeservice.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BankRepository extends JpaRepository<Bank, Long> {

     Optional<Bank> findByName(String bankName);






}
