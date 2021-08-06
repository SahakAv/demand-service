package com.disqo.providerservice.controller;

import com.disqo.providerservice.model.domain.ServiceType;
import com.disqo.providerservice.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/service-type")
public class ServiceTypeController {

    private final ServiceTypeService serviceTypeService;

    public ServiceTypeController(ServiceTypeService serviceTypeService) {
        this.serviceTypeService = serviceTypeService;
    }

    @GetMapping(value = "/{name}", produces = "application/json")
    public ResponseEntity<ServiceType> getByName(@PathVariable("name") String serviceTypeName) {
        return ResponseEntity.ok(serviceTypeService.getByName(serviceTypeName));
    }

    @GetMapping(produces = "application/json")
    public List<ServiceType> getAllServiceTypes() {
        return serviceTypeService.getAll();
    }
}
