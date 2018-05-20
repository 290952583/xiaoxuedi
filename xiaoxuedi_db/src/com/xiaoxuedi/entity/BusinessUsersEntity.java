package com.xiaoxuedi.entity;

import javax.persistence.*;

@Entity
@Table(name = "business_users", schema = "xiaoxuedi", catalog = "")
public class BusinessUsersEntity {
    private String id;
    private String name;
    private String schoolId;
    private String address;
    private String headImg;
    private String businessHours;
    private String placard;
    private String servicePhone;
    private String businessStatus;
    private Integer ordersSum;
    private Integer businessSum;
    private String withdrawAccount;
    private String withdrawType;
    private Integer startingPrice;
    private Integer distributionPrice;
    private String businessLicense;
    private String authStatus;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "school_id")
    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
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
    @Column(name = "head_img")
    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    @Basic
    @Column(name = "business_hours")
    public String getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(String businessHours) {
        this.businessHours = businessHours;
    }

    @Basic
    @Column(name = "placard")
    public String getPlacard() {
        return placard;
    }

    public void setPlacard(String placard) {
        this.placard = placard;
    }

    @Basic
    @Column(name = "service_phone")
    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }

    @Basic
    @Column(name = "business_status")
    public String getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(String businessStatus) {
        this.businessStatus = businessStatus;
    }

    @Basic
    @Column(name = "orders_sum")
    public Integer getOrdersSum() {
        return ordersSum;
    }

    public void setOrdersSum(Integer ordersSum) {
        this.ordersSum = ordersSum;
    }

    @Basic
    @Column(name = "business_sum")
    public Integer getBusinessSum() {
        return businessSum;
    }

    public void setBusinessSum(Integer businessSum) {
        this.businessSum = businessSum;
    }

    @Basic
    @Column(name = "withdraw_account")
    public String getWithdrawAccount() {
        return withdrawAccount;
    }

    public void setWithdrawAccount(String withdrawAccount) {
        this.withdrawAccount = withdrawAccount;
    }

    @Basic
    @Column(name = "withdraw_type")
    public String getWithdrawType() {
        return withdrawType;
    }

    public void setWithdrawType(String withdrawType) {
        this.withdrawType = withdrawType;
    }

    @Basic
    @Column(name = "starting_price")
    public Integer getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(Integer startingPrice) {
        this.startingPrice = startingPrice;
    }

    @Basic
    @Column(name = "distribution_price")
    public Integer getDistributionPrice() {
        return distributionPrice;
    }

    public void setDistributionPrice(Integer distributionPrice) {
        this.distributionPrice = distributionPrice;
    }

    @Basic
    @Column(name = "business_license")
    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    @Basic
    @Column(name = "auth_status")
    public String getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(String authStatus) {
        this.authStatus = authStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BusinessUsersEntity that = (BusinessUsersEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (schoolId != null ? !schoolId.equals(that.schoolId) : that.schoolId != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (headImg != null ? !headImg.equals(that.headImg) : that.headImg != null) return false;
        if (businessHours != null ? !businessHours.equals(that.businessHours) : that.businessHours != null)
            return false;
        if (placard != null ? !placard.equals(that.placard) : that.placard != null) return false;
        if (servicePhone != null ? !servicePhone.equals(that.servicePhone) : that.servicePhone != null) return false;
        if (businessStatus != null ? !businessStatus.equals(that.businessStatus) : that.businessStatus != null)
            return false;
        if (ordersSum != null ? !ordersSum.equals(that.ordersSum) : that.ordersSum != null) return false;
        if (businessSum != null ? !businessSum.equals(that.businessSum) : that.businessSum != null) return false;
        if (withdrawAccount != null ? !withdrawAccount.equals(that.withdrawAccount) : that.withdrawAccount != null)
            return false;
        if (withdrawType != null ? !withdrawType.equals(that.withdrawType) : that.withdrawType != null) return false;
        if (startingPrice != null ? !startingPrice.equals(that.startingPrice) : that.startingPrice != null)
            return false;
        if (distributionPrice != null ? !distributionPrice.equals(that.distributionPrice) : that.distributionPrice != null)
            return false;
        if (businessLicense != null ? !businessLicense.equals(that.businessLicense) : that.businessLicense != null)
            return false;
        return authStatus != null ? authStatus.equals(that.authStatus) : that.authStatus == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (schoolId != null ? schoolId.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (headImg != null ? headImg.hashCode() : 0);
        result = 31 * result + (businessHours != null ? businessHours.hashCode() : 0);
        result = 31 * result + (placard != null ? placard.hashCode() : 0);
        result = 31 * result + (servicePhone != null ? servicePhone.hashCode() : 0);
        result = 31 * result + (businessStatus != null ? businessStatus.hashCode() : 0);
        result = 31 * result + (ordersSum != null ? ordersSum.hashCode() : 0);
        result = 31 * result + (businessSum != null ? businessSum.hashCode() : 0);
        result = 31 * result + (withdrawAccount != null ? withdrawAccount.hashCode() : 0);
        result = 31 * result + (withdrawType != null ? withdrawType.hashCode() : 0);
        result = 31 * result + (startingPrice != null ? startingPrice.hashCode() : 0);
        result = 31 * result + (distributionPrice != null ? distributionPrice.hashCode() : 0);
        result = 31 * result + (businessLicense != null ? businessLicense.hashCode() : 0);
        result = 31 * result + (authStatus != null ? authStatus.hashCode() : 0);
        return result;
    }
}
