package com.xiaoxuedi.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;

import javax.persistence.*;

@Data
@Entity
@Table(name = "business_users")
public class BusinessUsersEntity {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @JoinColumn(name = "id")
    private String id;

    @JoinColumn(name = "name")
    private String name;

    @JoinColumn(name = "school_id")
    private String schoolId;

    @JoinColumn(name = "address")
    private String address;

    @JoinColumn(name = "head_img")
    private String headImg;

    @JoinColumn(name = "business_hours")
    private String businessHours;

    @JoinColumn(name = "placard")
    private String placard;

    @JoinColumn(name = "service_phone")
    private String servicePhone;

    @JoinColumn(name = "business_status")
    private String businessStatus;

    @JoinColumn(name = "orders_sum")
    private Integer ordersSum;

    @JoinColumn(name = "business_sum")
    private Integer businessSum;

    @JoinColumn(name = "withdraw_account")
    private BigDecimal withdrawAccount;

    @JoinColumn(name = "withdraw_type")
    private String withdrawType;

    @JoinColumn(name = "starting_price")
    private BigDecimal startingPrice;

    @JoinColumn(name = "distribution_price")
    private BigDecimal distributionPrice;

    @JoinColumn(name = "business_license")
    private String businessLicense;

    @JoinColumn(name = "auth_status")
    private String authStatus;


}
