package com.xiaoxuedi.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class ImageEntity {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @JoinColumn(name = "id")
    private String id;

    @JoinColumn(name = "name")
    private String name;

    @JoinColumn(name = "imgurl")
    private String imgurl;

    @JoinColumn(name = "create_time")
    private Timestamp createTime;


}
