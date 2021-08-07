package com.disqo.providerservice.controller;

import com.disqo.providerservice.model.domain.ServiceType;
import com.disqo.providerservice.service.ServiceTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/service-type")
@Slf4j
public class ServiceTypeController {

    private final ServiceTypeService serviceTypeService;

    public ServiceTypeController(ServiceTypeService serviceTypeService) {
        this.serviceTypeService = serviceTypeService;
    }

    @GetMapping(value = "/{name}", produces = "application/json")
    public ResponseEntity<ServiceType> getByName(@PathVariable("name") String serviceTypeName) {
        log.info("Requested to get service type by name {}", serviceTypeName);
        return ResponseEntity.ok(serviceTypeService.getByName(serviceTypeName));
    }

    @GetMapping(produces = "application/json")
    public List<ServiceType> getAllServiceTypes() {
        log.info("Requested to get all service types");
        return serviceTypeService.getAll();
    }
}
