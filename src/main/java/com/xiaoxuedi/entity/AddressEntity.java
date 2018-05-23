package com.xiaoxuedi.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Data
@Entity
public class AddressEntity implements BelongUser {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @JoinColumn(name = "id")
    private String id;

    @JoinColumn(name = "name")
    private String name;

    @JoinColumn(name = "phone")
    private String phone;

    @JoinColumn(name = "address")
    private String address;

    @JoinColumn(name = "coordinate")
    private String coordinate;

    @JoinColumn(name = "remark")
    private String remark;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UsersEntity user;
}
