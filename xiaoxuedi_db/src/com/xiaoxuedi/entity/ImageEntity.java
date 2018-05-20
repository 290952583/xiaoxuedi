package com.xiaoxuedi.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "image", schema = "xiaoxuedi", catalog = "")
public class ImageEntity {
    private String name;
    private String imgurl;
    private Timestamp createTime;

    @Id
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "imgurl")
    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImageEntity that = (ImageEntity) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (imgurl != null ? !imgurl.equals(that.imgurl) : that.imgurl != null) return false;
        return createTime != null ? createTime.equals(that.createTime) : that.createTime == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (imgurl != null ? imgurl.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
