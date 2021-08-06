package com.disqo.customerservice.controller;

import com.disqo.customerservice.model.payload.request.CreateServiceRequest;
import com.disqo.customerservice.model.payload.response.ServiceRequest;
import com.disqo.customerservice.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping(value = "/request", consumes = "application/json", produces = "application/json")
    public ServiceRequest requestService(@RequestBody CreateServiceRequest  createServiceRequest){
        return customerService.createServiceRequest(createServiceRequest);
    }
}
