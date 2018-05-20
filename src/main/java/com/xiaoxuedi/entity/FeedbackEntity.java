package com.xiaoxuedi.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class FeedbackEntity implements BelongUser {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @JoinColumn(name = "id")
    private String id;

    @JoinColumn(name = "feedback")
    private String feedback;

    @JoinColumn(name = "create_time")
    private Timestamp createTime;

    @JoinColumn(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
