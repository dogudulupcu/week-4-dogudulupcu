package com.patika.kredinbizdeservice.client;

import com.patika.kredinbizdeservice.client.dto.request.GarantiBankApplicationRequest;
import com.patika.kredinbizdeservice.client.dto.response.ApplicationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "garantiBankService", url = "localhost:8081")
public interface GarantiBankServiceClient {

    @PostMapping("api/garantibank/v1/applications")
    ApplicationResponse createApplication(@RequestBody GarantiBankApplicationRequest request);

    @GetMapping("api/garantibank/v1/applications")
    List<ApplicationResponse> getAll();

    @GetMapping("api/garantibank/v1/applications/{userId}")
    List<ApplicationResponse> getAllByUserId(@PathVariable(value = "userId") long userId);

}
