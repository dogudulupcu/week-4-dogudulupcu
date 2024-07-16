package com.patika.kredinbizdeservice.service;


import com.patika.kredinbizdeservice.entity.Bank;
import com.patika.kredinbizdeservice.entity.dto.BankDTO;
import com.patika.kredinbizdeservice.repository.BankRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BankServiceTest {

    @InjectMocks
    private BankService bankService;

    @Mock
    private BankRepository bankRepository;

    @Test
    public void should_create_bank_successfully() {
        // Given
        Mockito.when(bankRepository.save(Mockito.any(Bank.class))).thenReturn(prepareBank());

        // When
        BankDTO bankResponse = bankService.save(prepareBank());

        // Then
        assertThat(bankResponse).isNotNull();
        assertThat(bankResponse.getName()).isEqualTo(prepareBank().getName());
        assertThat(bankResponse.getInterestRate()).isEqualTo(prepareBank().getInterestRate());
        assertThat(bankResponse.getId()).isEqualTo(prepareBank().getId());

        verify(bankRepository, times(1)).save(Mockito.any(Bank.class));
    }

    @Test
    public void should_return_bank_by_id_successfully() {
        // Given
        Mockito.when(bankRepository.findById(prepareBank().getId())).thenReturn(Optional.of(prepareBank()));

        // When
        BankDTO bankResponse = bankService.findById(prepareBank().getId());

        // Then
        assertThat(bankResponse).isNotNull();
        assertThat(bankResponse.getName()).isEqualTo(prepareBank().getName());
        assertThat(bankResponse.getInterestRate()).isEqualTo(prepareBank().getInterestRate());
        assertThat(bankResponse.getId()).isEqualTo(prepareBank().getId());

        verify(bankRepository, times(1)).findById(prepareBank().getId());

    }

    @Test
    public void should_return_bank_by_name_successfully(){
        // Given
        Mockito.when(bankRepository.findByName(prepareBank().getName())).thenReturn(Optional.of(prepareBank()));

        // When
        BankDTO bankResponse = bankService.findByName(prepareBank().getName());

        // Then
        assertThat(bankResponse).isNotNull();
        assertThat(bankResponse.getName()).isEqualTo(prepareBank().getName());
        assertThat(bankResponse.getInterestRate()).isEqualTo(prepareBank().getInterestRate());
        assertThat(bankResponse.getId()).isEqualTo(prepareBank().getId());

        verify(bankRepository, times(1)).findByName(prepareBank().getName());
    }

    @Test
    public void should_return_all_banks_successfully(){
        // Given
        Mockito.when(bankRepository.findAll()).thenReturn(prepareBankList());

        // When
        var bankListResponse = bankService.getAll();

        // Then
        assertThat(bankListResponse).isNotNull();
        assertThat(bankListResponse.size()).isEqualTo(prepareBankList().size());
        for(int i = 0;i<bankListResponse.size();i++){
            assertThat(bankListResponse.get(i).getName()).isEqualTo(prepareBankList().get(i).getName());
            assertThat(bankListResponse.get(i).getInterestRate()).isEqualTo(prepareBankList().get(i).getInterestRate());
            assertThat(bankListResponse.get(i).getId()).isEqualTo(prepareBankList().get(i).getId());
        }

        verify(bankRepository, times(1)).findAll();

    }

    @Test
    public void should_return_updated_bank_successfully(){
        // Given
        Mockito.when(bankRepository.findByName(prepareBank().getName())).thenReturn(Optional.of(prepareBank()));
        Mockito.when(bankRepository.save(Mockito.any(Bank.class))).thenReturn(prepareBank());

        // When
        BankDTO bankResponse = bankService.update(prepareBank().getName(), prepareBank());

        // Then
        assertThat(bankResponse).isNotNull();
        assertThat(bankResponse.getName()).isEqualTo(prepareBank().getName());
        assertThat(bankResponse.getInterestRate()).isEqualTo(prepareBank().getInterestRate());
        assertThat(bankResponse.getId()).isEqualTo(prepareBank().getId());

        verify(bankRepository, times(1)).findByName(prepareBank().getName());
        verify(bankRepository, times(1)).save(Mockito.any(Bank.class));
    }

    private List<Bank> prepareBankList() {
        return List.of(prepareBank(), prepareBank2(), prepareBank3());
    }


    public Bank prepareBank(){
        Bank bank = new Bank();
        bank.setId(1L);
        bank.setName("Test Bank");
        bank.setInterestRate(1.0);
        return bank;
    }

    public Bank prepareBank2(){
        Bank bank = new Bank();
        bank.setId(2L);
        bank.setName("Test Bank2");
        bank.setInterestRate(1.0);
        return bank;
    }

    public Bank prepareBank3(){
        Bank bank = new Bank();
        bank.setId(3L);
        bank.setName("Test Bank3");
        bank.setInterestRate(1.0);
        return bank;
    }
}
