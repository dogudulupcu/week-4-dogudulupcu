package com.patika.kredinbizdeservice.controller;

import com.patika.kredinbizdeservice.entity.CreditCard;
import com.patika.kredinbizdeservice.entity.dto.CreditCardDTO;
import com.patika.kredinbizdeservice.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/creditCards")
public class CreditCardController {

    @Autowired
    @Qualifier(value = "creditCardService")
    private CreditCardService creditCardService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard create(@RequestBody CreditCard creditCard) {
        return creditCardService.save(creditCard);
    }

    // **Bankaların kredi kartlarını ve bu kartların kampanyalarını listeleyen end-point. Burada kredikartlarını
    // **listelerken kampanyalarını da listeleyebilirsiniz.
    @GetMapping
    public List<CreditCardDTO> getAll() {
        return creditCardService.getAll();
    }

    @GetMapping("/{name}")
    public CreditCard findByName(@PathVariable String name) {
        return creditCardService.findByName(name);
    }






}
