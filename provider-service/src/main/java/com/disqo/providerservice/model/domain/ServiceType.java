package com.disqo.providerservice.model.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "servicetype")
public class ServiceType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String serviceName;
}
