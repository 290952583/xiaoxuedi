package com.xiaoxuedi.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sys_users")
public class SysUsersEntity {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @JoinColumn(name = "id")
    private String id;

    @JoinColumn(name = "name")
    private String name;

    @JoinColumn(name = "username")
    private String username;

    @JoinColumn(name = "password")
    private String password;

    @JoinColumn(name = "user_type")
    private String userType;

    @JoinColumn(name = "auth_status")
    private String authStatus;

    @JoinColumn(name = "photo")
    private String photo;


}
