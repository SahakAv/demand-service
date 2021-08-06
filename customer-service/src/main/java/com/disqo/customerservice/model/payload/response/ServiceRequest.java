package com.disqo.customerservice.model.payload.response;

import lombok.Data;

@Data
public class ServiceRequest {

    private Long id;

    private String serviceType;

    private String address;

    private String date;


}
