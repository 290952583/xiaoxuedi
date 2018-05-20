package com.xiaoxuedi.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "pay_info", schema = "xiaoxuedi", catalog = "")
public class PayInfoEntity {
    private int id;
    private Integer ordersId;
    private String payType;
    private String payStatus;
    private Timestamp createTime;
    private Timestamp finishTime;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "orders_id")
    public Integer getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Integer ordersId) {
        this.ordersId = ordersId;
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
    @Column(name = "pay_status")
    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
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
    @Column(name = "finish_time")
    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PayInfoEntity that = (PayInfoEntity) o;

        if (id != that.id) return false;
        if (ordersId != null ? !ordersId.equals(that.ordersId) : that.ordersId != null) return false;
        if (payType != null ? !payType.equals(that.payType) : that.payType != null) return false;
        if (payStatus != null ? !payStatus.equals(that.payStatus) : that.payStatus != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        return finishTime != null ? finishTime.equals(that.finishTime) : that.finishTime == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (ordersId != null ? ordersId.hashCode() : 0);
        result = 31 * result + (payType != null ? payType.hashCode() : 0);
        result = 31 * result + (payStatus != null ? payStatus.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (finishTime != null ? finishTime.hashCode() : 0);
        return result;
    }
}
