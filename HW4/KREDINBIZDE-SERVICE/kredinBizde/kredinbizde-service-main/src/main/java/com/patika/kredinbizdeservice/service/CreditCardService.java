package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.entity.Bank;
import com.patika.kredinbizdeservice.entity.CreditCard;
import com.patika.kredinbizdeservice.entity.dto.CreditCardDTO;
import com.patika.kredinbizdeservice.repository.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;
    private final BankService bankService;

    public CreditCard save(CreditCard creditCard) {
      //  creditCard.setBank(bankService.findById(creditCard.getBank().getId()));
        //Bank bank=bankService.findById(creditCard.getBank().getId());
       // creditCard.setBank(bank);
        return creditCardRepository.save(creditCard);
    }

    public List<CreditCardDTO> getAll() {
        List<CreditCard> creditCardList = creditCardRepository.findAll();
        return CreditCardDTO.creditCardToStringDTOList(creditCardList);
    }

    public CreditCard findByName(String name) {
        return creditCardRepository.findByName(name).orElse(null);

    }

    public CreditCard update(String cardName, CreditCard creditCard) {
        Optional<CreditCard> foundCreditCard= creditCardRepository.findByName(cardName);

        if(foundCreditCard.isPresent()){
            foundCreditCard.get().setName(creditCard.getName());
            foundCreditCard.get().setFee(creditCard.getFee());
            creditCardRepository.delete(creditCard);
            creditCardRepository.save(foundCreditCard.get());
            return foundCreditCard.get();

        }

        return null;

    }
}
