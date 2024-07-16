package com.patika.garantibankservice.controller;

import com.patika.garantibankservice.dto.response.ApplicationResponse;
import com.patika.garantibankservice.dto.request.ApplicationRequest;
import com.patika.garantibankservice.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/garantibank/v1/applications")
@RequiredArgsConstructor
@Slf4j
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public ApplicationResponse createApplication(@RequestBody ApplicationRequest request) {
       log.info("istek atıldı UserId: {} LoanId: {}",request.getUserId(),request.getLoanId());

        return applicationService.createApplication(request);
    }

    @GetMapping
    public ResponseEntity<List<ApplicationResponse>> getAll(){
        return ResponseEntity.ok(applicationService.getAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ApplicationResponse>> getAllByUserId(@PathVariable(value = "userId")  long userId){
        return ResponseEntity.ok(applicationService.getAllByUserId(userId));
    }


}
