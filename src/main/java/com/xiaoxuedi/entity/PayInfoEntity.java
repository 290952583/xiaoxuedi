package com.xiaoxuedi.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "pay_info")
public class PayInfoEntity {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @JoinColumn(name = "id")
    private int id;

    @JoinColumn(name = "orders_id")
    private String ordersId;

    @JoinColumn(name = "pay_type")
    private String payType;

    @JoinColumn(name = "pay_status")
    private String payStatus;

    @JoinColumn(name = "create_time")
    private Timestamp createTime;

    @JoinColumn(name = "finish_time")
    private Timestamp finishTime;


}
