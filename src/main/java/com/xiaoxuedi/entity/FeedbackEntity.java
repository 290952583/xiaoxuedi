package com.xiaoxuedi.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "feedback")
public class FeedbackEntity implements BelongUser {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @JoinColumn(name = "id")
    private String id;

    @Column(nullable = false, length = 5120)
    @Length(min = 10)
    private String feedback;

    @JoinColumn(name = "create_time")
    private Timestamp createTime;

    @JoinColumn(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UsersEntity user;

}
