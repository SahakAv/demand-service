package com.disqo.providerservice.model.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ServiceRequestResponse {
    private String id;
    private String serviceType;
    private LocalDate date;
    private String address;
    private RequestStatus status;
    private String owner;
    private String assigned;

}
