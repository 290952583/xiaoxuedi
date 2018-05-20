package com.xiaoxuedi.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "orders", schema = "xiaoxuedi", catalog = "")
public class OrdersEntity {
    private String id;
    private String orderNo;
    private int orderAmount;
    private Integer couponAmount;
    private Integer deliveryAmount;
    private Timestamp createTime;
    private Timestamp receiveTime;
    private Timestamp forecastTime;
    private Timestamp finishTime;
    private String type;
    private String payType;
    private String address;
    private String businessId;
    private String acceptUserId;
    private String remark;
    private String status;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "order_no")
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Basic
    @Column(name = "order_amount")
    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    @Basic
    @Column(name = "coupon_amount")
    public Integer getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(Integer couponAmount) {
        this.couponAmount = couponAmount;
    }

    @Basic
    @Column(name = "delivery_amount")
    public Integer getDeliveryAmount() {
        return deliveryAmount;
    }

    public void setDeliveryAmount(Integer deliveryAmount) {
        this.deliveryAmount = deliveryAmount;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "receive_time")
    public Timestamp getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Timestamp receiveTime) {
        this.receiveTime = receiveTime;
    }

    @Basic
    @Column(name = "forecast_time")
    public Timestamp getForecastTime() {
        return forecastTime;
    }

    public void setForecastTime(Timestamp forecastTime) {
        this.forecastTime = forecastTime;
    }

    @Basic
    @Column(name = "finish_time")
    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "pay_type")
    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "business_id")
    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    @Basic
    @Column(name = "accept_user_id")
    public String getAcceptUserId() {
        return acceptUserId;
    }

    public void setAcceptUserId(String acceptUserId) {
        this.acceptUserId = acceptUserId;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdersEntity that = (OrdersEntity) o;

        if (orderAmount != that.orderAmount) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (orderNo != null ? !orderNo.equals(that.orderNo) : that.orderNo != null) return false;
        if (couponAmount != null ? !couponAmount.equals(that.couponAmount) : that.couponAmount != null) return false;
        if (deliveryAmount != null ? !deliveryAmount.equals(that.deliveryAmount) : that.deliveryAmount != null)
            return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (receiveTime != null ? !receiveTime.equals(that.receiveTime) : that.receiveTime != null) return false;
        if (forecastTime != null ? !forecastTime.equals(that.forecastTime) : that.forecastTime != null) return false;
        if (finishTime != null ? !finishTime.equals(that.finishTime) : that.finishTime != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (payType != null ? !payType.equals(that.payType) : that.payType != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (businessId != null ? !businessId.equals(that.businessId) : that.businessId != null) return false;
        if (acceptUserId != null ? !acceptUserId.equals(that.acceptUserId) : that.acceptUserId != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        return status != null ? status.equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (orderNo != null ? orderNo.hashCode() : 0);
        result = 31 * result + orderAmount;
        result = 31 * result + (couponAmount != null ? couponAmount.hashCode() : 0);
        result = 31 * result + (deliveryAmount != null ? deliveryAmount.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (receiveTime != null ? receiveTime.hashCode() : 0);
        result = 31 * result + (forecastTime != null ? forecastTime.hashCode() : 0);
        result = 31 * result + (finishTime != null ? finishTime.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (payType != null ? payType.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (businessId != null ? businessId.hashCode() : 0);
        result = 31 * result + (acceptUserId != null ? acceptUserId.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
