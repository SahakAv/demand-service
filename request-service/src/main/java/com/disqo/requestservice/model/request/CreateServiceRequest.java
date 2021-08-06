package com.disqo.requestservice.model.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateServiceRequest {
    private String serviceType;

    private String address;

    private LocalDate date;
}
