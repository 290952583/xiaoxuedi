package com.xiaoxuedi.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
public class MissionEntity implements BelongUser {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @JoinColumn(name = "id")
    private String id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JoinColumn(name = "mission_no")
    private String missionNo;

    @JoinColumn(name = "address")
    private String address;

    @JoinColumn(name = "price")
    private int price;

    @JoinColumn(name = "status")
    private String status;

    @JoinColumn(name = "create_time")
    private Timestamp createTime;

    @JoinColumn(name = "distribution_time")
    private String distributionTime;

    @JoinColumn(name = "finish_time")
    private Timestamp finishTime;

    @JoinColumn(name = "type")
    private String type;

    @JoinColumn(name = "accept_user_id")
    private String acceptUserId;

    @JoinColumn(name = "school")
    private String school;

    @JoinColumn(name = "delivery")
    private String delivery;

    @JoinColumn(name = "get_code")
    private String getCode;

    @JoinColumn(name = "remark")
    private String remark;

    @JoinColumn(name = "coupon_amount")
    private BigDecimal couponAmount;


}
