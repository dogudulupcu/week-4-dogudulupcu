package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.client.GarantiBankServiceClient;
import com.patika.kredinbizdeservice.client.dto.request.GarantiBankApplicationRequest;
import com.patika.kredinbizdeservice.client.dto.response.ApplicationResponse;
import com.patika.kredinbizdeservice.enums.ApplicationStatus;
import com.patika.kredinbizdeservice.converter.ApplicationConverter;
import com.patika.kredinbizdeservice.dto.request.ApplicationRequest;
import com.patika.kredinbizdeservice.entity.Application;
import com.patika.kredinbizdeservice.entity.Loan;
import com.patika.kredinbizdeservice.entity.User;
import com.patika.kredinbizdeservice.entity.dto.ApplicationDTO;
import com.patika.kredinbizdeservice.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationConverter applicationConverter;
    private final UserService userService;
    private final LoanService loanService;
    private final GarantiBankServiceClient garantiBankServiceClient;


    public ApplicationDTO save(Application application) {
        application.setCreateDate(LocalDate.now());
        return prepareApplicationDTO(applicationRepository.save(application));
    }

    public List<ApplicationDTO> getAll() {
        return prepareApplicationDTOList(applicationRepository.findAll());
    }

    public List<ApplicationDTO> findByUserEmail(String email) {
        return prepareApplicationDTOList(applicationRepository.findByUserEmail(email).orElse(null));
    }

    public ApplicationDTO findById(long id) {
        return prepareApplicationDTO(applicationRepository.findById(id).orElse(null));

    }

    public ApplicationDTO update(long applicationId, Application application) {
        Optional<Application> foundApplication = applicationRepository.findById(applicationId);

        if(foundApplication.isPresent()){
            Application updatedApplication = foundApplication.get();
            updatedApplication.setApplicationStatus(application.getApplicationStatus());
            updatedApplication.setId(application.getId());
            updatedApplication.setLoan(application.getLoan());
            updatedApplication.setUser(application.getUser());
            applicationRepository.delete(application);
            applicationRepository.save(updatedApplication);
            return prepareApplicationDTO(updatedApplication);
        }
            return null;



    }

    public ApplicationDTO createApplication(ApplicationRequest request) {

        User user = userService.getById(request.getUserId());
        log.info("User bulundu {}",user.getEmail());

        Loan loan = loanService.getById(request.getLoanId());
        log.info("Loan bulundu {} {}",loan.getId(),loan.getLoanType());

        Application application= applicationConverter.toApplication(user,loan);

        Application savedApplication = applicationRepository.save(application);

        ApplicationResponse applicationResponse = garantiBankServiceClient.createApplication(prepareGarantiBankApplicationRequest(user,loan));

        return prepareApplicationDTO(savedApplication);

    }

    public List<ApplicationDTO> getAllGarantiBankApplications(){
        List<ApplicationResponse> responseList = garantiBankServiceClient.getAll();
        log.info("applications: {}", responseList);
        return prepareApplicationDTOList(applicationRepository.findAll());
    }

    public List<ApplicationDTO> getGarantiBankApplicationsByUserId(long userId) {
      List<ApplicationResponse> responseList =garantiBankServiceClient.getAllByUserId(userId);
        if(applicationRepository.getAllApplicationsByUserId(userId).isPresent()){
            return prepareApplicationDTOList(applicationRepository.getAllApplicationsByUserId(userId).get());
        }
        return null;


    }

    private GarantiBankApplicationRequest prepareGarantiBankApplicationRequest(User user, Loan loan) {
        GarantiBankApplicationRequest applicationRequest = new GarantiBankApplicationRequest();
        applicationRequest.setUserId(user.getId());
        applicationRequest.setLoanId(loan.getId());
        return applicationRequest;
    }

    public ApplicationDTO prepareApplicationDTO(Application application){
        return ApplicationDTO.builder()
                .id(application.getId())
                .userId(application.getUser().getId())
                .loanId(application.getLoan().getId())
                .amount(application.getLoan().getAmount())
                .createDate(application.getCreateDate())
                .applicationStatus(application.getApplicationStatus())
                .build();
    }

    public List<ApplicationDTO> prepareApplicationDTOList(List<Application> applications){
        return applications.stream().map(this::prepareApplicationDTO).collect(Collectors.toList());
    }


}
