package com.xiaoxuedi.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
public class DeliveryEntity {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @JoinColumn(name = "id")
    private String id;

    @JoinColumn(name = "name")
    private String name;

    @JoinColumn(name = "phone")
    private String phone;

    @JoinColumn(name = "address")
    private String address;


}
