package com.patika.kredinbizdeservice.repository;

import com.patika.kredinbizdeservice.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ApplicationRepository extends JpaRepository<Application, Long>{

     Optional<List<Application>> findByUserEmail(String email);


    Optional<List<Application>> findByUserId(Long id);


    Optional<List<Application>> getAllApplicationsByUserId(Long id);





}
