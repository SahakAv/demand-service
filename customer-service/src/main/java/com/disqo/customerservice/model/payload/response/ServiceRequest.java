package com.disqo.customerservice.model.payload.response;

import lombok.Data;

import java.time.LocalDate;


@Data
public class ServiceRequest {

    private Long id;

    private String serviceType;

    private String address;

    private LocalDate date;

    private RequestStatus status;

    private String assigned;


}
