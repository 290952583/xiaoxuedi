package com.xiaoxuedi.entity;

import javax.persistence.*;

@Entity
@Table(name = "order_commodity", schema = "xiaoxuedi", catalog = "")
public class OrderCommodityEntity {
    private String id;
    private String orderId;
    private String commodityId;
    private String commodityName;
    private int commoditySum;
    private int commodityAmountSum;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "order_id")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "commodity_id")
    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    @Basic
    @Column(name = "commodity_name")
    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    @Basic
    @Column(name = "commodity_sum")
    public int getCommoditySum() {
        return commoditySum;
    }

    public void setCommoditySum(int commoditySum) {
        this.commoditySum = commoditySum;
    }

    @Basic
    @Column(name = "commodity_amount_sum")
    public int getCommodityAmountSum() {
        return commodityAmountSum;
    }

    public void setCommodityAmountSum(int commodityAmountSum) {
        this.commodityAmountSum = commodityAmountSum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderCommodityEntity that = (OrderCommodityEntity) o;

        if (commoditySum != that.commoditySum) return false;
        if (commodityAmountSum != that.commodityAmountSum) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        if (commodityId != null ? !commodityId.equals(that.commodityId) : that.commodityId != null) return false;
        return commodityName != null ? commodityName.equals(that.commodityName) : that.commodityName == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        result = 31 * result + (commodityId != null ? commodityId.hashCode() : 0);
        result = 31 * result + (commodityName != null ? commodityName.hashCode() : 0);
        result = 31 * result + commoditySum;
        result = 31 * result + commodityAmountSum;
        return result;
    }
}
