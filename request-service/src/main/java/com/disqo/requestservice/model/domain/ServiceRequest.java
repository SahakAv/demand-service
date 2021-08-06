package com.disqo.requestservice.model.domain;

import com.disqo.requestservice.model.request.CreateServiceRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "servicerequest")
@NoArgsConstructor
public class ServiceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String serviceType;

    private String address;

    private String date;

    private String owner;

    private RequestStatus requestStatus;

    public ServiceRequest(CreateServiceRequest request, String owner){
        this.serviceType = request.getServiceType();
        this.address = request.getAddress();
        this.date = request.getServiceType();
        this.owner = owner;
        this.requestStatus = RequestStatus.NEW;
    }


}
