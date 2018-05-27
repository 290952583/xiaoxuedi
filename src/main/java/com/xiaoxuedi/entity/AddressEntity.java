package com.xiaoxuedi.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Data
@Entity
@Table(name = "address")
public class AddressEntity extends BaseEntity implements BelongUser {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @JoinColumn(name = "id")
    private String id;

    @JoinColumn(name = "name", nullable = false)
    private String name;

    @JoinColumn(name = "phone", nullable = false)
    private String phone;

    @JoinColumn(name = "address", nullable = false)
    private String address;

    @JoinColumn(name = "details", nullable = false, columnDefinition = "varchar(100) COMMENT '详细地址'")
    private String details;

    @JoinColumn(name = "coordinate")
    private String coordinate;

    @JoinColumn(name = "remark")
    private String remark;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "varchar(32) not null COMMENT '用户id'")
    private UsersEntity user;
}
