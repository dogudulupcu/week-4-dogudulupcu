package com.patika.kredinbizdeservice.controller;


import com.patika.kredinbizdeservice.dto.request.ApplicationRequest;
import com.patika.kredinbizdeservice.entity.Application;
import com.patika.kredinbizdeservice.entity.dto.ApplicationDTO;
import com.patika.kredinbizdeservice.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    //Kredi başvurularını kaydeden end-point.
    @PostMapping
    public ApplicationDTO create(@RequestBody Application application) {
        return applicationService.save(application);
    }

    @PostMapping("/createApplication")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApplicationDTO> createApplication(@RequestBody ApplicationRequest request){
        return ResponseEntity.ok().body(applicationService.createApplication(request));
    }

    @GetMapping("/getAllGarantiBankApplications")
    public ResponseEntity<List<ApplicationDTO>> getAllGarantiBankApplications(){
        return ResponseEntity.ok().body(applicationService.getAllGarantiBankApplications());
    }

    @GetMapping("/getGarantiBankApplicationsByUserId/{userId}")
    public ResponseEntity<List<ApplicationDTO>> getGarantiBankApplicationsByUserId(@PathVariable(value = "userId") long userId){
        return ResponseEntity.ok().body(applicationService.getGarantiBankApplicationsByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<List<ApplicationDTO>> getAll() {
        return ResponseEntity.ok().body(applicationService.getAll());
    }

    //email adresi ile kullanıcının bütün başvurularını listeleyen end-point.
    @GetMapping("/{email}")
    public ResponseEntity<List<ApplicationDTO>> findByUserEmail(@PathVariable String email) {
        return ResponseEntity.ok().body(applicationService.findByUserEmail(email));
    }

    @GetMapping("/byId/{applicationId}")
    public ResponseEntity<ApplicationDTO> findById(@PathVariable Long applicationId) {
        return ResponseEntity.ok().body(applicationService.findById(applicationId));
    }

    @PutMapping("/{applicationId}")
    public ResponseEntity<ApplicationDTO> update(@PathVariable Long applicationId, @RequestBody Application application) {
        return ResponseEntity.ok().body(applicationService.update(applicationId, application));
    }




}
