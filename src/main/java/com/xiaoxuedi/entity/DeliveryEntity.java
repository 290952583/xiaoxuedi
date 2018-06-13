package com.xiaoxuedi.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "delivery")
public class DeliveryEntity {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @JoinColumn(name = "id")
    private String id;//id

    @JoinColumn(name = "name")
    private String name;//快递公司名称

    @JoinColumn(name = "phone")
    private String phone;//电话

    @JoinColumn(name = "address")
    private String address;//地址


}
