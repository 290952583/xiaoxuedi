package com.xiaoxuedi.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "orders")
public class OrdersEntity extends BaseEntity implements BelongUser {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @JoinColumn(name = "id")
    private String id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UsersEntity user;

    @JoinColumn(name = "order_no")
    private String orderNo;

    @JoinColumn(name = "order_amount")
    private BigDecimal orderAmount;
    
    @JoinColumn(name = "actual_amount")
    private BigDecimal actualAmount;//实际金额

    @JoinColumn(name = "coupon_amount")
    private BigDecimal couponAmount;
    
    @JoinColumn(name = "coupon_id")
    private String coupon_id;//优惠券id

    @JoinColumn(name = "delivery_amount")
    private BigDecimal deliveryAmount;

    @JoinColumn(name = "create_time")
    private Timestamp createTime;

    @JoinColumn(name = "receive_time")
    private Timestamp receiveTime;

    @JoinColumn(name = "forecast_time")
    private Timestamp forecastTime;

    @JoinColumn(name = "finish_time")
    private Timestamp finishTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @JoinColumn(name = "pay_type")
    private String payType;

    @JoinColumn(name = "address")
    private String address;

    @JoinColumn(name = "accept_user_id")
    private String acceptUserId;

    @JoinColumn(name = "remark")
    private String remark;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.WAIT;


    public enum Type {
        CHARGE,
        CHARGE_SUCCEEDED,
        TRANSFER,
        TRANSFER_SUCCEEDED,
        TRANSFER_FAILED,
        RELEASE,
        FINISH,
    }
    
    public enum Status {
        WAIT,//等待
        PROCESSING,//处理
        CANCEL,//取消
        FINISH,//完成
    }
}
