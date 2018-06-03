package com.xiaoxuedi.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "op_by", columnDefinition = "varchar(32) COMMENT '更新人'")
    private String opBy;

    @Column(name = "op_time", columnDefinition = "datetime COMMENT '更新时间'")
    private Date opTime;

    @Column(name = "create_by", columnDefinition = "varchar(32) COMMENT '创建人'")
    private String createBy;

    @Column(name = "create_time", columnDefinition = "datetime COMMENT '创建时间'")
    private Date createTime = new Date();

    @Column(name = "delete_time", columnDefinition = "bigint(20) NOT NULL DEFAULT '0' COMMENT '删除标记 0：未删除；其他：删除'")
    private Long deleteTime = 0L;
}
