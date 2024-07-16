package com.patika.kredinbizdeservice.repository;

import com.patika.kredinbizdeservice.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CreditCardRepository extends JpaRepository<CreditCard, Long>{
     Optional<CreditCard> findByName(String name);



}
