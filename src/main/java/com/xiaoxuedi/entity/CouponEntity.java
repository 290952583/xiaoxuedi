package com.xiaoxuedi.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "coupon")
public class CouponEntity {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @JoinColumn(name = "id")
    private String id;

    @JoinColumn(name = "name")
    private String name;

    @JoinColumn(name = "type")
    private String type;

    @JoinColumn(name = "amount")
    private BigDecimal amount;

    @JoinColumn(name = "full_amount_reduction")
    private BigDecimal fullAmountReduction;

    @JoinColumn(name = "start_time")
    private Timestamp startTime;

    @JoinColumn(name = "end_time")
    private Timestamp endTime;

    @JoinColumn(name = "status")
    private String status;//valid,有效，其他不可用
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UsersEntity user;


}
