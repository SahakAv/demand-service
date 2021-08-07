package com.disqo.providerservice.model.domain;

import com.disqo.providerservice.model.request.CreateServiceProviderRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "serviceprovider")
@NoArgsConstructor
public class ServiceProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private ServiceType serviceType;

    private String name;

    private String owner;


    public ServiceProvider(CreateServiceProviderRequest request, ServiceType serviceType, String username) {
        this.serviceType = serviceType;
        this.name = request.getServiceProviderName();
        this.owner = username;
    }
}
