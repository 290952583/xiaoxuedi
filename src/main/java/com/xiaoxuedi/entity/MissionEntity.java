package com.xiaoxuedi.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "mission")
public class MissionEntity implements BelongUser {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @JoinColumn(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UsersEntity user;

    @JoinColumn(name = "mission_no")
    private String missionNo;

    @JoinColumn(name = "address")
    private String address;

    @JoinColumn(name = "price")
    private BigDecimal price;
    
    @JoinColumn(name = "actual_amount")
    private BigDecimal actualAmount;//实际金额

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.WAIT;

    @JoinColumn(name = "create_time")
    @CreationTimestamp
    private Timestamp createTime;

    @JoinColumn(name = "distribution_time")
    private String distributionTime;

    @JoinColumn(name = "finish_time")
    private Timestamp finishTime;

    @JoinColumn(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "accept_user_id")
    private UsersEntity acceptUser;

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
    
    @JoinColumn(name = "coupon_id")
    private String coupon_id;//优惠券id

    public enum Status {
        WAIT,//等待
        PROCESSING,//处理
        CANCEL,//取消
        FINISH,//完成
    }
}
