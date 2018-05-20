package com.xiaoxuedi.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
public class SchoolEntity {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @JoinColumn(name = "id")
    private String id;

    @JoinColumn(name = "school")
    private String school;

    @JoinColumn(name = "coordinate")
    private String coordinate;

    @JoinColumn(name = "address")
    private String address;

}
