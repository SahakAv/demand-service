package com.disqo.requestservice.model.domain;

import com.disqo.requestservice.model.request.CreateServiceRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

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

    private LocalDate date;

    private String owner;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    //Assigned provider name
    private String assigned;

    public ServiceRequest(CreateServiceRequest request, String owner){
        this.serviceType = request.getServiceType();
        this.address = request.getAddress();
        this.date = request.getDate();
        this.owner = owner;
        this.status = RequestStatus.NEW;
    }


}
