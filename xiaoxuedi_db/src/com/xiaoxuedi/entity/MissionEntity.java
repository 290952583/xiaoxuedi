package com.xiaoxuedi.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "mission", schema = "xiaoxuedi", catalog = "")
public class MissionEntity {
    private String id;
    private String missionNo;
    private String address;
    private int price;
    private String status;
    private Timestamp createTime;
    private String distributionTime;
    private Timestamp finishTime;
    private String type;
    private String acceptUserId;
    private String school;
    private String delivery;
    private String getCode;
    private String remark;
    private Integer couponAmount;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "mission_no")
    public String getMissionNo() {
        return missionNo;
    }

    public void setMissionNo(String missionNo) {
        this.missionNo = missionNo;
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
    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    @Column(name = "distribution_time")
    public String getDistributionTime() {
        return distributionTime;
    }

    public void setDistributionTime(String distributionTime) {
        this.distributionTime = distributionTime;
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
    @Column(name = "accept_user_id")
    public String getAcceptUserId() {
        return acceptUserId;
    }

    public void setAcceptUserId(String acceptUserId) {
        this.acceptUserId = acceptUserId;
    }

    @Basic
    @Column(name = "school")
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Basic
    @Column(name = "delivery")
    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    @Basic
    @Column(name = "get_code")
    public String getGetCode() {
        return getCode;
    }

    public void setGetCode(String getCode) {
        this.getCode = getCode;
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
    @Column(name = "coupon_amount")
    public Integer getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(Integer couponAmount) {
        this.couponAmount = couponAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MissionEntity that = (MissionEntity) o;

        if (price != that.price) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (missionNo != null ? !missionNo.equals(that.missionNo) : that.missionNo != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (distributionTime != null ? !distributionTime.equals(that.distributionTime) : that.distributionTime != null)
            return false;
        if (finishTime != null ? !finishTime.equals(that.finishTime) : that.finishTime != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (acceptUserId != null ? !acceptUserId.equals(that.acceptUserId) : that.acceptUserId != null) return false;
        if (school != null ? !school.equals(that.school) : that.school != null) return false;
        if (delivery != null ? !delivery.equals(that.delivery) : that.delivery != null) return false;
        if (getCode != null ? !getCode.equals(that.getCode) : that.getCode != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        return couponAmount != null ? couponAmount.equals(that.couponAmount) : that.couponAmount == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (missionNo != null ? missionNo.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (distributionTime != null ? distributionTime.hashCode() : 0);
        result = 31 * result + (finishTime != null ? finishTime.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (acceptUserId != null ? acceptUserId.hashCode() : 0);
        result = 31 * result + (school != null ? school.hashCode() : 0);
        result = 31 * result + (delivery != null ? delivery.hashCode() : 0);
        result = 31 * result + (getCode != null ? getCode.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (couponAmount != null ? couponAmount.hashCode() : 0);
        return result;
    }
}
