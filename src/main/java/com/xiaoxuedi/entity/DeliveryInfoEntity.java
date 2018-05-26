package com.xiaoxuedi.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "delivery_info")
public class DeliveryInfoEntity {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @JoinColumn(name = "id")
    private String id;

    @JoinColumn(name = "user_id")
    private String userId;

    @JoinColumn(name = "business_id")
    private String businessId;

    @JoinColumn(name = "delivery_id")
    private String deliveryId;

    @JoinColumn(name = "status")
    private String status;

    @JoinColumn(name = "type")
    private String type;

    @JoinColumn(name = "create_time")
    private Timestamp createTime;

    @JoinColumn(name = "finish_time")
    private Timestamp finishTime;

    @JoinColumn(name = "amount")
    private BigDecimal amount;


}
