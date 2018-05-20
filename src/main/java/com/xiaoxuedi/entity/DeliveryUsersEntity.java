package com.xiaoxuedi.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
public class DeliveryUsersEntity {

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

    @JoinColumn(name = "mobile")
    private String mobile;

    @JoinColumn(name = "sex")
    private String sex;

    @JoinColumn(name = "photo")
    private String photo;

    @JoinColumn(name = "status")
    private String status;

    @JoinColumn(name = "type")
    private String type;

    @JoinColumn(name = "id_card")
    private String idCard;


}
