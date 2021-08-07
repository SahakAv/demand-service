package com.disqo.customerservice.controller;

import com.disqo.customerservice.model.payload.request.CreateServiceRequest;
import com.disqo.customerservice.model.payload.response.ServiceRequest;
import com.disqo.customerservice.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping(value = "/request", consumes = "application/json", produces = "application/json")
    public ServiceRequest requestService(@RequestBody CreateServiceRequest createServiceRequest, @RequestHeader String username) {
        log.info("User {} requested to create service {}", username, createServiceRequest.getServiceType());
        return customerService.createServiceRequest(createServiceRequest, username);
    }

    @GetMapping(value = "/request", produces = "application/json")
    public List<ServiceRequest> getRequests(@RequestHeader String username) {
        log.info("User {} requested to get all requests", username);
        return customerService.getCustomerRequests(username);
    }
}
