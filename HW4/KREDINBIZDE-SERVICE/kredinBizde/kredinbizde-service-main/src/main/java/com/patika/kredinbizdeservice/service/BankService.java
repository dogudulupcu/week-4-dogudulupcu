package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.entity.Bank;
import com.patika.kredinbizdeservice.entity.dto.BankDTO;
import com.patika.kredinbizdeservice.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankService {

    private final BankRepository bankRepository;

    public BankDTO save(Bank bank){
        bankRepository.save(bank);
        return prepareBankDTO(bank);
    }

    public List<BankDTO> getAll(){
       return prepareBankDTOList(bankRepository.findAll());
    }

    public BankDTO findByName(String name){
      return prepareBankDTO(bankRepository.findByName(name).orElse(null));

    }

    public BankDTO findById(Long id){
        return prepareBankDTO(bankRepository.findById(id).orElse(null));
    }

    public BankDTO update(String name, Bank bank){
        Optional<Bank> bankToUpdate = bankRepository.findByName(name);
        if(bankToUpdate.isPresent()){
            Bank updatedBank = bankToUpdate.get();
            updatedBank.setName(bank.getName());
            updatedBank.setInterestRate(bank.getInterestRate());
            bankRepository.delete(bank);
            bankRepository.save(updatedBank);
            return prepareBankDTO(updatedBank);
        }
        return null;

    }



    public BankDTO prepareBankDTO(Bank bank){
        return BankDTO.builder()
                .id(bank.getId())
                .name(bank.getName())
                .interestRate(bank.getInterestRate())
                .build();
    }

    public List<BankDTO> prepareBankDTOList(List<Bank> bankList){
        return bankList.stream().map(this::prepareBankDTO).toList();
    }


}
