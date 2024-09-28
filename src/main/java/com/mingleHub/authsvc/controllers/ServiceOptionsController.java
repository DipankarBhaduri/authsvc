package com.mingleHub.authsvc.controllers;

import com.mingleHub.authsvc.dto.auth.AuthenticationRequest;
import com.mingleHub.authsvc.dto.category.CategoryResponseDTO;
import com.mingleHub.authsvc.services.impl.ServiceOptionSvcImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/service")
public class ServiceOptionsController {

    private final ServiceOptionSvcImpl serviceOptionSvc;

    @Autowired
    public ServiceOptionsController (
            ServiceOptionSvcImpl serviceOptionSv
    ) {
        this.serviceOptionSvc = serviceOptionSv;
    }

    @GetMapping("/category")
    public CategoryResponseDTO getCategoryList (
            @RequestBody AuthenticationRequest request
    ) {
        log.info("INFO :: get category list :: {}", request.getEmail());
        return serviceOptionSvc.getCategoryList();
    }
}
