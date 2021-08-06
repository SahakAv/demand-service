package com.disqo.customerservice.controller;

import com.disqo.customerservice.model.payload.request.CreateServiceRequest;
import com.disqo.customerservice.model.payload.response.ServiceRequest;
import com.disqo.customerservice.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping(value = "/request", consumes = "application/json", produces = "application/json")
    public ServiceRequest requestService(@RequestBody CreateServiceRequest createServiceRequest, @RequestHeader String username) {
        return customerService.createServiceRequest(createServiceRequest, username);
    }

    @GetMapping(value = "/request", produces = "application/json")
    public List<ServiceRequest> getRequests(@RequestHeader String username) {
        return customerService.getCustomerRequests(username);
    }
}
