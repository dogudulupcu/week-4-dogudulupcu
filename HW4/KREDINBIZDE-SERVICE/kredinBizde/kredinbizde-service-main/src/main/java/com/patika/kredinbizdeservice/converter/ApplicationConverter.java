package com.patika.kredinbizdeservice.converter;

import com.patika.kredinbizdeservice.entity.Application;
import com.patika.kredinbizdeservice.entity.Loan;
import com.patika.kredinbizdeservice.entity.User;
import com.patika.kredinbizdeservice.entity.dto.ApplicationDTO;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Builder
public class ApplicationConverter {

    public Application toApplication( User user, Loan loan) {
        Application application = new Application();
        application.setUser(user);
        application.setLoan(loan);
        application.setCreateDate(LocalDate.now());
        return application;
    }


    public ApplicationDTO toApplicationDto(Application application) {
        return ApplicationDTO.builder()
                .id(application.getId())
                .amount(application.getLoan().getAmount())
                .createDate(application.getCreateDate())
                .applicationStatus(application.getApplicationStatus())
                .build();

    }
}
