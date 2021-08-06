package com.disqo.providerservice.controller;

import com.disqo.providerservice.model.domain.ServiceProvider;
import com.disqo.providerservice.model.request.CreateServiceProviderRequest;
import com.disqo.providerservice.model.response.ServiceProviderResponse;
import com.disqo.providerservice.service.ServiceProviderService;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping("/provider")
public class ProviderController {

    private final ServiceProviderService serviceProviderService;


    public ProviderController(ServiceProviderService serviceProviderService, DiscoveryClient discoveryClient) {
        this.serviceProviderService = serviceProviderService;
    }


    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
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

    @GetMapping( produces = "application/json")
    public List<ServiceProviderResponse> getAllProviders(){
        return serviceProviderService.
    }


}
