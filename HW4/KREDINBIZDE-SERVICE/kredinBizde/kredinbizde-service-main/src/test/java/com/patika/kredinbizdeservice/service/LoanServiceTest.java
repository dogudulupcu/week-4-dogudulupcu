package com.patika.kredinbizdeservice.service;


import com.patika.kredinbizdeservice.entity.HouseLoan;
import com.patika.kredinbizdeservice.entity.Loan;
import com.patika.kredinbizdeservice.entity.VehicleLoan;
import com.patika.kredinbizdeservice.enums.LoanType;
import com.patika.kredinbizdeservice.repository.LoanRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class LoanServiceTest {

    @InjectMocks
    private LoanService loanService;

    @Mock
    private LoanRepository loanRepository;

    @Test
    public void should_create_loan_successfully() {
        // Given
        Mockito.when(loanRepository.save(Mockito.any(Loan.class))).thenReturn(prepareHouseLoan());


        // When
        Loan houseLoan = loanService.save(prepareHouseLoan());
        // Then
        assertThat(houseLoan).isNotNull();
        assertThat(houseLoan.getLoanType()).isEqualTo(prepareHouseLoan().getLoanType());
        assertThat(houseLoan.getId()).isEqualTo(prepareHouseLoan().getId());
        assertThat(houseLoan.getAmount()).isEqualTo(prepareHouseLoan().getAmount());
        assertThat(houseLoan.getInterestRate()).isEqualTo(prepareHouseLoan().getInterestRate());
        assertThat(houseLoan.getLoanType()).isEqualTo(prepareHouseLoan().getLoanType());

        verify(loanRepository, times(1)).save(Mockito.any(Loan.class));


    }

    @Test
    public void should_return_loan_by_id_successfully() {
        // Given
        Mockito.when(loanRepository.findById(prepareHouseLoan().getId())).thenReturn(Optional.of(prepareHouseLoan()));

        // When
        Loan houseLoan = loanService.getById(1L);

        // Then
        assertThat(houseLoan).isNotNull();
        assertThat(houseLoan.getLoanType()).isEqualTo(prepareHouseLoan().getLoanType());
        assertThat(houseLoan.getId()).isEqualTo(prepareHouseLoan().getId());
        assertThat(houseLoan.getAmount()).isEqualTo(prepareHouseLoan().getAmount());
        assertThat(houseLoan.getInterestRate()).isEqualTo(prepareHouseLoan().getInterestRate());
        assertThat(houseLoan.getLoanType()).isEqualTo(prepareHouseLoan().getLoanType());

        verify(loanRepository, times(1)).findById(Mockito.anyLong());
    }

    @Test
    public void should_delete_loan_successfully() {
        // Given
        Loan houseLoan = prepareHouseLoan();
        // When
        loanService.delete(houseLoan);
        // Then
        verify(loanRepository, times(1)).delete(Mockito.any(Loan.class));
    }

    @Test
    public void should_return_by_loan_type_successfully() {
        // Given
        Mockito.when(loanRepository.findByLoanType(LoanType.KONUT_KREDISI)).thenReturn(Optional.of(prepareLoanList()));

        // When
        List<Loan> loanList = loanService.findByLoanType(LoanType.KONUT_KREDISI);

        // Then
        assertThat(loanList).isNotNull();
        assertThat(loanList.size()).isEqualTo(2);
        assertThat(loanList.get(0).getLoanType()).isEqualTo(prepareHouseLoan().getLoanType());
        assertThat(loanList.get(1).getLoanType()).isEqualTo(prepareVehicleLoan().getLoanType());

        verify(loanRepository, times(1)).findByLoanType(LoanType.KONUT_KREDISI);

    }

    @Test
    public void should_return_loan_by_id(){
        // Given
        Mockito.when(loanRepository.findById(1L)).thenReturn(Optional.of(prepareHouseLoan()));

        // When
        Loan loan = loanService.getById(1L);

        // Then
        assertThat(loan).isNotNull();
        assertThat(loan.getId()).isEqualTo(prepareHouseLoan().getId());
        assertThat(loan.getLoanType()).isEqualTo(prepareHouseLoan().getLoanType());
        assertThat(loan.getAmount()).isEqualTo(prepareHouseLoan().getAmount());
        assertThat(loan.getInterestRate()).isEqualTo(prepareHouseLoan().getInterestRate());

        verify(loanRepository, times(1)).findById(1L);
    }


    public HouseLoan prepareHouseLoan() {
        HouseLoan houseLoan = new HouseLoan();
        houseLoan.setAmount(new BigDecimal(100000));;
        houseLoan.setInterestRate(1.2);
        houseLoan.setId(1L);
        houseLoan.setLoanType(LoanType.KONUT_KREDISI);
        houseLoan.setCity("ISTANBUL");
       return houseLoan;
    }

    public VehicleLoan prepareVehicleLoan() {
        VehicleLoan vehicleLoan = new VehicleLoan();
        vehicleLoan.setAmount(new BigDecimal(100000));;
        vehicleLoan.setInterestRate(1.2);
        vehicleLoan.setId(1L);
        vehicleLoan.setLoanType(LoanType.ARAC_KREDISI);
        vehicleLoan.setVehicleInformation("BMW");
        return vehicleLoan;
    }

    public List<Loan> prepareLoanList() {
        return List.of(prepareHouseLoan(), prepareVehicleLoan());
    }
}
