package com.patika.kredinbizdeservice.service;


import com.patika.kredinbizdeservice.client.GarantiBankServiceClient;
import com.patika.kredinbizdeservice.client.dto.request.GarantiBankApplicationRequest;
import com.patika.kredinbizdeservice.client.dto.response.ApplicationResponse;
import com.patika.kredinbizdeservice.converter.ApplicationConverter;
import com.patika.kredinbizdeservice.dto.request.ApplicationRequest;
import com.patika.kredinbizdeservice.entity.*;
import com.patika.kredinbizdeservice.entity.dto.ApplicationDTO;
import com.patika.kredinbizdeservice.enums.ApplicationStatus;
import com.patika.kredinbizdeservice.enums.LoanType;
import com.patika.kredinbizdeservice.repository.ApplicationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceTest {

    @InjectMocks
    private ApplicationService applicationService;

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private ApplicationConverter applicationConverter;

    @Mock
    private UserService userService;

    @Mock
    private LoanService loanService;

    @Mock
    private GarantiBankServiceClient garantiBankServiceClient;

    @Test
    public void should_create_loan_application_successfully() {
        // Given
        Mockito.when(applicationRepository.save(Mockito.any(Application.class))).thenReturn(prepareApplication());


        // When
        ApplicationDTO applicationDTO = applicationService.save(prepareApplication());

        // Then
        assertThat(applicationDTO).isNotNull();
        assertThat(applicationDTO.getApplicationStatus()).isEqualTo(ApplicationStatus.INITIAL);
        assertThat(applicationDTO.getAmount()).isEqualTo(prepareApplication().getAmount());
        assertThat(applicationDTO.getLoanId()).isEqualTo(prepareApplication().getLoan().getId());
        assertThat(applicationDTO.getUserId()).isEqualTo(prepareApplication().getUser().getId());
        assertThat(applicationDTO.getCreateDate()).isEqualTo(prepareApplication().getCreateDate());
        assertThat(applicationDTO.getCreditCardId()).isNull();

        verify(applicationRepository, times(1)).save(Mockito.any(Application.class));
    }

    @Test
    public void should_return_application_by_id_successfully() {
        // Given
        Mockito.when(applicationRepository.findById(prepareApplication().getId())).thenReturn(Optional.of(prepareApplication()));

        // When
        ApplicationDTO applicationDTO = applicationService.findById(1L);

        // Then
        assertThat(applicationDTO).isNotNull();
        assertThat(applicationDTO.getApplicationStatus()).isEqualTo(ApplicationStatus.INITIAL);
        assertThat(applicationDTO.getAmount()).isEqualTo(prepareApplication().getAmount());
        assertThat(applicationDTO.getLoanId()).isEqualTo(prepareApplication().getLoan().getId());
        assertThat(applicationDTO.getUserId()).isEqualTo(prepareApplication().getUser().getId());
        assertThat(applicationDTO.getCreateDate()).isEqualTo(prepareApplication().getCreateDate());
        assertThat(applicationDTO.getId()).isEqualTo(prepareApplication().getId());
        assertThat(applicationDTO.getCreditCardId()).isNull();

        verify(applicationRepository, times(1)).findById(1L);
    }

    @Test
    public void should_return_updated_loan_application_successfully() {
        // Given
        Mockito.when(applicationRepository.findById(prepareApplication().getId())).thenReturn(Optional.of(prepareApplication()));
        Mockito.when(applicationRepository.save(Mockito.any(Application.class))).thenReturn(prepareUpdatedApplication());

        // when
        ApplicationDTO updatedApplicationDTO = applicationService.update(prepareApplication().getId(), prepareUpdatedApplication());

        // then
        assertThat(updatedApplicationDTO).isNotNull();
        assertThat(updatedApplicationDTO.getApplicationStatus()).isEqualTo(prepareUpdatedApplication().getApplicationStatus());
        assertThat(updatedApplicationDTO.getAmount()).isEqualTo(prepareUpdatedApplication().getAmount());
        assertThat(updatedApplicationDTO.getLoanId()).isEqualTo(prepareUpdatedApplication().getLoan().getId());
        assertThat(updatedApplicationDTO.getUserId()).isEqualTo(prepareUpdatedApplication().getUser().getId());
        assertThat(updatedApplicationDTO.getCreateDate()).isEqualTo(prepareUpdatedApplication().getCreateDate());
        assertThat(updatedApplicationDTO.getId()).isEqualTo(prepareUpdatedApplication().getId());
        assertThat(updatedApplicationDTO.getCreditCardId()).isNull();

        verify(applicationRepository, times(1)).findById(prepareApplication().getId());
        verify(applicationRepository, times(1)).save(Mockito.any(Application.class));


    }

    @Test
    public void should_return_all_applications_successfully() {
        // Given
        Mockito.when(applicationRepository.findAll()).thenReturn(prepareApplicationList());

        // When
        List<ApplicationDTO> applicationDTOList = applicationService.getAll();

        // Then
        assertThat(applicationDTOList).isNotNull();
        assertThat(applicationDTOList.size()).isEqualTo(prepareApplicationList().size());
        verify(applicationRepository, times(1)).findAll();
    }

    @Test
    public void should_return_application_by_user_email_successfully() {
        // Given
        Mockito.when(applicationRepository.findByUserEmail(prepareUser().getEmail())).thenReturn(Optional.of(prepareApplicationList()));

        // When
        List<ApplicationDTO> applicationDTOList = applicationService.findByUserEmail(prepareUser().getEmail());

        // Then
        assertThat(applicationDTOList).isNotNull();
        assertThat(applicationDTOList.size()).isEqualTo(prepareApplicationList().size());
        verify(applicationRepository, times(1)).findByUserEmail(prepareUser().getEmail());
    }

    @Test
    public void should_return_garantiBankApplication_successfully(){
        ApplicationRequest request = prepareApplicationRequest();
        Application application = prepareApplication();
        Application savedApplication = prepareApplication();
        ApplicationDTO applicationDTO = prepareApplicationDTO();

        when(userService.getById(request.getUserId())).thenReturn(prepareUser());
        when(loanService.getById(request.getLoanId())).thenReturn(prepareHouseLoan());
        when(applicationConverter.toApplication(prepareUser(), prepareHouseLoan())).thenReturn(application);
        when(applicationRepository.save(application)).thenReturn(savedApplication);
        when(garantiBankServiceClient.createApplication(Mockito.any(GarantiBankApplicationRequest.class)))
                .thenReturn(prepareApplicationResponse());
        when(applicationConverter.toApplicationDto(savedApplication)).thenReturn(applicationDTO);

        // When
        ApplicationDTO result = applicationService.createApplication(request);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(applicationDTO);

        // Verify
        verify(userService, times(1)).getById(request.getUserId());
        verify(loanService, times(1)).getById(request.getLoanId());
        verify(applicationRepository, times(1)).save(application);
        verify(garantiBankServiceClient, times(1)).createApplication(Mockito.any(GarantiBankApplicationRequest.class));
        
    }




    private Application prepareApplication() {
        Application application = new Application();
        application.setApplicationStatus(ApplicationStatus.INITIAL);
        application.setId(1L);
        application.setLoan(prepareHouseLoan());
        application.setUser(prepareUser());
        application.setCreateDate(LocalDate.now());
        application.setAmount(new BigDecimal(1000));
        return application;

    }

    private HouseLoan prepareHouseLoan() {
        HouseLoan loan = new HouseLoan();
        loan.setAmount(new BigDecimal(1000));
        loan.setInterestRate(2.0);
        loan.setId(1L);
        loan.setBank(prepareBank());
        loan.setCity("Istanbul");
        loan.setLoanType(LoanType.KONUT_KREDISI);
        loan.setInstallment(12);
     return loan;
    }

    private Bank prepareBank() {
        Bank bank = new Bank();
        bank.setId(1L);
        bank.setName("Garanti Bank");
        bank.setInterestRate(2.0);
        return bank;
    }

    private User prepareUser() {
        User user = new User();
        user.setName("test");
        user.setSurname("test");
        user.setEmail("test@gmail.com");
        user.setPassword("123456");
        user.setIsActive(true);
        user.setId(1L);
        return user;
    }

    private Application prepareUpdatedApplication() {
        Application application = new Application();
        application.setApplicationStatus(ApplicationStatus.INITIAL);
        application.setId(2L);
        application.setLoan(prepareHouseLoan());
        application.setUser(prepareUser2());
        application.setCreateDate(LocalDate.now());
        application.setAmount(new BigDecimal(1000));
        return application;

    }

    private User prepareUser2() {
        User user = new User();
        user.setName("test2");
        user.setSurname("test2");
        user.setEmail("test2@gmail.com");
        user.setPassword("123456");
        user.setIsActive(true);

        return user;

    }
    private Application prepareUpdatedApplication2() {
        Application application = new Application();
        application.setApplicationStatus(ApplicationStatus.INITIAL);
        application.setId(2L);
        application.setLoan(prepareHouseLoan());
        application.setUser(prepareUser2());
        application.setCreateDate(LocalDate.now());
        application.setAmount(new BigDecimal(12121));
        return application;

    }

    private Application prepareUpdatedApplication3() {
        Application application = new Application();
        application.setApplicationStatus(ApplicationStatus.INITIAL);
        application.setId(2L);
        application.setLoan(prepareHouseLoan());
        application.setUser(prepareUser2());
        application.setCreateDate(LocalDate.now());
        application.setAmount(new BigDecimal(66666));
        return application;

    }

    private List<Application> prepareApplicationList() {
        return List.of(prepareApplication(), prepareUpdatedApplication(), prepareUpdatedApplication2(), prepareUpdatedApplication3()) ;
    }

    private ApplicationRequest prepareApplicationRequest(){
        ApplicationRequest applicationRequest = new ApplicationRequest();
        applicationRequest.setUserId(1L);
        applicationRequest.setLoanId(1L);
        return applicationRequest;
    }

    private ApplicationResponse prepareApplicationResponse(){
        ApplicationResponse applicationResponse = new ApplicationResponse();
        applicationResponse.setUserId(1L);
        applicationResponse.setLoanId(1L);
        return applicationResponse;
    }

    private ApplicationDTO prepareApplicationDTO(){
        ApplicationDTO applicationDTO = new ApplicationDTO();
        applicationDTO.setId(1L);
        applicationDTO.setUserId(1L);
        applicationDTO.setLoanId(1L);
        return applicationDTO;
    }

}
