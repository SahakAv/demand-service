package com.disqo.requestservice.model.request;

import lombok.Data;

@Data
public class CreateServiceRequest {
    private String serviceType;

    private String address;

    private String date;
}
