package com.patika.garantibankservice.service;

import com.patika.garantibankservice.converter.ApplicationConverter;
import com.patika.garantibankservice.dto.request.ApplicationRequest;
import com.patika.garantibankservice.dto.response.ApplicationResponse;
import com.patika.garantibankservice.entity.Application;
import com.patika.garantibankservice.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final ApplicationConverter applicationConverter;

    public ApplicationResponse createApplication(ApplicationRequest request) {

        Application application = applicationConverter.toApplication(request);

        return applicationConverter.toResponse(applicationRepository.save(application));
    }


    public List<ApplicationResponse> getAll() {
        List<Application> applications = applicationRepository.findAll();
        log.info("applications: {}", applications);
        return applicationConverter.toResponseList(applications);
    }

    public List<ApplicationResponse> getAllByUserId(long userId) {
        List<Application> applications = applicationRepository.findAll();
        List<Application> userApplications = new ArrayList<>();
        for (Application application : applications) {
            if (application.getUserId() == userId) {
                userApplications.add(application);
            }
        }
        return applicationConverter.toResponseList(userApplications);
    }
}
