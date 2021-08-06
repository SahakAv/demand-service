package com.disqo.requestservice.controller;

import com.disqo.requestservice.model.domain.ServiceRequest;
import com.disqo.requestservice.model.request.CreateServiceRequest;
import com.disqo.requestservice.service.RequestService;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/request")
public class ServiceRequestController {


    private final RequestService requestService;

    public ServiceRequestController(RequestService requestService) {
        this.requestService = requestService;
    }


    @PostMapping(consumes = "application/json", produces = "application/json")
    public ServiceRequest create(@RequestBody CreateServiceRequest request, @RequestHeader("username") String username) {
        return requestService.create(request,username);
    }
}
