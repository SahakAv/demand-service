package com.disqo.requestservice.controller;

import com.disqo.requestservice.model.domain.ServiceRequest;
import com.disqo.requestservice.model.request.CreateServiceRequest;
import com.disqo.requestservice.model.request.ServiceProviders;
import com.disqo.requestservice.model.request.ServiceTypes;
import com.disqo.requestservice.service.RequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/request")
public class ServiceRequestController {


    private final RequestService requestService;

    public ServiceRequestController(RequestService requestService) {
        this.requestService = requestService;
    }


    @PostMapping(consumes = "application/json", produces = "application/json")
    public ServiceRequest create(@RequestBody CreateServiceRequest request, @RequestHeader("username") String username) {
        return requestService.create(request, username);
    }

    @GetMapping(produces = "application/json")
    public List<ServiceRequest> getConsumerRequests(@RequestHeader("username") String username) {
        return requestService.getConsumerRequests(username);
    }

    @GetMapping(value = "/{requestId}", produces = "application/json")
    public ServiceRequest getById(@PathVariable Long requestId) {
        return requestService.getById(requestId);
    }

    @PostMapping(value = "/new", produces = "application/json")
    public List<ServiceRequest> getNewServiceRequests(@RequestBody ServiceTypes serviceTypes) {
        return requestService.getNewRequests(serviceTypes.getServiceTypes());
    }

    @GetMapping(value = "/assign/{requestId}/{providerName}", produces = "application/json")
    public ServiceRequest assignToProvider(@PathVariable Long requestId, @PathVariable String providerName) {
        return requestService.assignToProvider(requestId, providerName);
    }

    @GetMapping(value = "/close/{requestId}", produces = "application/json")
    public ServiceRequest close(@PathVariable Long requestId) {
        return requestService.close(requestId);
    }

    @PostMapping(value = "/provider", produces = "application/json")
    public List<ServiceRequest> getProvidersAssignedRequests(@RequestBody ServiceProviders serviceProviders) {
        return requestService.getProvidersAssignedRequests(serviceProviders);
    }
}