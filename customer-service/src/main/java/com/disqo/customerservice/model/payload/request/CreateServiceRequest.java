package com.disqo.customerservice.model.payload.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.Optional;

@Data
public class CreateServiceRequest {
    private String serviceType;

    private Optional<String> serviceProvider;

    private String address;

    private LocalDate date;
}
