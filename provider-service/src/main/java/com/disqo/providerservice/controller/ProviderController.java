package com.disqo.providerservice.controller;

import com.disqo.providerservice.model.domain.ServiceProvider;
import com.disqo.providerservice.model.request.CreateServiceProviderRequest;
import com.disqo.providerservice.model.response.ServiceProviderResponse;
import com.disqo.providerservice.model.response.ServiceRequestResponse;
import com.disqo.providerservice.service.ServiceProviderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping("/provider")
public class ProviderController {

    private final ServiceProviderService serviceProviderService;


    public ProviderController(ServiceProviderService serviceProviderService) {
        this.serviceProviderService = serviceProviderService;
    }


    @PostMapping(consumes = "application/json", produces = "application/json")
    public ServiceProvider create(@RequestBody CreateServiceProviderRequest request, @RequestHeader String username) {
        return serviceProviderService.create(request, username);
    }


    @GetMapping(value = "/type/{serviceType}", produces = "application/json")
    public List<ServiceProviderResponse> getAllByServiceType(@PathVariable String serviceType) {
        return serviceProviderService.getAllByServiceType(serviceType);
    }


    @GetMapping(value = "/name/{name}", produces = "application/json")
    public ServiceProviderResponse getProviderByName(@PathVariable String name) {
        return serviceProviderService.getByName(name);
    }

    @GetMapping(produces = "application/json")
    public List<ServiceProviderResponse> getAllProviders() {
        return serviceProviderService.getAll();
    }


    @GetMapping(value = "/requests/new", produces = "application/json")
    //Provide active requests can be assigned to service provider
    public List<ServiceRequestResponse> getRequestedServices(@RequestHeader String username) {
        return serviceProviderService.getActives(username);
    }

    @GetMapping(value = "/request/accept/{requestId}/{providerName}", produces = "application/json")
    //Accept request
    public ServiceRequestResponse acceptRequest(@RequestHeader String username, @PathVariable Long requestId,
                                                @PathVariable String providerName) {
        return serviceProviderService.acceptRequest(username, requestId, providerName);
    }

    @GetMapping(value = "/request/close/{requestId}", produces = "application/json")
    //Close request
    public ServiceRequestResponse closeRequest(@RequestHeader String username, @PathVariable Long requestId) {
        return serviceProviderService.closeRequest(username, requestId);
    }

    @GetMapping(value = "/request/accepted", produces = "application/json")
    //Accept request
    public List<ServiceRequestResponse> getAcceptedRequests(@RequestHeader String username) {
        return serviceProviderService.getAcceptedRequests(username);
    }

}
