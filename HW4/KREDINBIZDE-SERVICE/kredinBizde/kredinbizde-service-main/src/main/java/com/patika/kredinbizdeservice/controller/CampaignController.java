package com.patika.kredinbizdeservice.controller;

import com.patika.kredinbizdeservice.entity.Campaign;
import com.patika.kredinbizdeservice.entity.dto.CampaignDTO;
import com.patika.kredinbizdeservice.service.CampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
@RequiredArgsConstructor
public class CampaignController {

    private final CampaignService campaignService;



    @GetMapping
    public ResponseEntity<List<CampaignDTO>> getAll() {
        return ResponseEntity.ok(campaignService.getAll());
    }

    @PostMapping
    public ResponseEntity<CampaignDTO> save(@RequestBody Campaign campaign) {
        return ResponseEntity.ok(campaignService.save(campaign));
    }

    //Sistemdeki bütün kampanyaları en güncelden eski tarihe doğru listeleyen end-point
    @GetMapping("/inOrder")
    public ResponseEntity<List<CampaignDTO>> getAllAscendingCreationDate() {
        return ResponseEntity.ok(campaignService.getAllAscendingCreationDate());
    }



}
