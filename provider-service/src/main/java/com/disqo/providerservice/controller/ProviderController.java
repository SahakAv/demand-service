package com.disqo.providerservice.controller;

import com.disqo.providerservice.model.domain.ServiceProvider;
import com.disqo.providerservice.model.request.CreateServiceProviderRequest;
import com.disqo.providerservice.model.response.ServiceProviderResponse;
import com.disqo.providerservice.model.response.ServiceRequestResponse;
import com.disqo.providerservice.service.ServiceProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping("/provider")
@Slf4j
public class ProviderController {

    private final ServiceProviderService serviceProviderService;


    public ProviderController(ServiceProviderService serviceProviderService) {
        this.serviceProviderService = serviceProviderService;
    }


    @PostMapping(consumes = "application/json", produces = "application/json")
    public ServiceProvider create(@RequestBody CreateServiceProviderRequest request, @RequestHeader String username) {
        log.info("User {} requested to create service provider {}", username, request);
        return serviceProviderService.create(request, username);
    }


    @GetMapping(value = "/type/{serviceType}", produces = "application/json")
    public List<ServiceProviderResponse> getAllByServiceType(@PathVariable String serviceType) {
        log.info("Requested to get all service providers for service type {}", serviceType);
        return serviceProviderService.getAllByServiceType(serviceType);
    }


    @GetMapping(value = "/name/{name}", produces = "application/json")
    public ServiceProviderResponse getProviderByName(@PathVariable String name) {
        log.info("Requested to get service provider by name {} ", name);
        return serviceProviderService.getByName(name);
    }

    @GetMapping(produces = "application/json")
    public List<ServiceProviderResponse> getAllProviders() {
        log.info("Requested to get all services providers");
        return serviceProviderService.getAll();
    }


    @GetMapping(value = "/requests/new", produces = "application/json")
    //Active requests that can be assigned to service provider
    public List<ServiceRequestResponse> getRequestedServices(@RequestHeader String username) {
        log.info("User {} requested to get all new requests", username);
        return serviceProviderService.getActives(username);
    }

    @GetMapping(value = "/request/accept/{requestId}/{providerName}", produces = "application/json")
    //Accept request
    public ServiceRequestResponse acceptRequest(@RequestHeader String username, @PathVariable Long requestId,
                                                @PathVariable String providerName) {
        log.info("User {} requested to accept request {} for provider {}", username, requestId, providerName);
        return serviceProviderService.acceptRequest(username, requestId, providerName);
    }

    @GetMapping(value = "/request/close/{requestId}", produces = "application/json")
    //Close request
    public ServiceRequestResponse closeRequest(@RequestHeader String username, @PathVariable Long requestId) {
        log.info("User {} requested to close request {}", username, requestId);
        return serviceProviderService.closeRequest(username, requestId);
    }

    @GetMapping(value = "/request/accepted", produces = "application/json")
    //Get accepted requests
    public List<ServiceRequestResponse> getAcceptedRequests(@RequestHeader String username) {
        log.info("User {} requested to get all accepted requests", username);
        return serviceProviderService.getAcceptedRequests(username);
    }

}
