package com.patika.kredinbizdeservice.controller;

import com.patika.kredinbizdeservice.entity.Bank;
import com.patika.kredinbizdeservice.entity.dto.BankDTO;
import com.patika.kredinbizdeservice.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banks")
@RequiredArgsConstructor
public class BankController {

    private final BankService bankService;

    @PostMapping
    public ResponseEntity<BankDTO> create(@RequestBody Bank bank) {
        return ResponseEntity.ok(bankService.save(bank));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<List<BankDTO>> getAll() {
        return ResponseEntity.ok(bankService.getAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity<BankDTO> findByName(@PathVariable String name) {
        return ResponseEntity.ok(bankService.findByName(name));
    }

}
