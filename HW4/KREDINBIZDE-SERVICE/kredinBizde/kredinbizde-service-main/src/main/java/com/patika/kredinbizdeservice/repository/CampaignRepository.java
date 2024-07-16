package com.patika.kredinbizdeservice.repository;

import com.patika.kredinbizdeservice.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CampaignRepository extends JpaRepository<Campaign, Long> {
     Optional<Campaign> findByTitle(String title);

}
