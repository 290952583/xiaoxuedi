package com.xiaoxuedi.entity;

import javax.persistence.*;

@Entity
@Table(name = "commodity", schema = "xiaoxuedi", catalog = "")
public class CommodityEntity {
    private String id;
    private String type;
    private String name;
    private String salesSum;
    private Integer price;
    private Integer originalPrice;
    private Integer costPrice;
    private String imgs;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "sales_sum")
    public String getSalesSum() {
        return salesSum;
    }

    public void setSalesSum(String salesSum) {
        this.salesSum = salesSum;
    }

    @Basic
    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "original_price")
    public Integer getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Integer originalPrice) {
        this.originalPrice = originalPrice;
    }

    @Basic
    @Column(name = "cost_price")
    public Integer getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Integer costPrice) {
        this.costPrice = costPrice;
    }

    @Basic
    @Column(name = "imgs")
    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommodityEntity that = (CommodityEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (salesSum != null ? !salesSum.equals(that.salesSum) : that.salesSum != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (originalPrice != null ? !originalPrice.equals(that.originalPrice) : that.originalPrice != null)
            return false;
        if (costPrice != null ? !costPrice.equals(that.costPrice) : that.costPrice != null) return false;
        return imgs != null ? imgs.equals(that.imgs) : that.imgs == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (salesSum != null ? salesSum.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (originalPrice != null ? originalPrice.hashCode() : 0);
        result = 31 * result + (costPrice != null ? costPrice.hashCode() : 0);
        result = 31 * result + (imgs != null ? imgs.hashCode() : 0);
        return result;
    }
}
