package com.xiaoxuedi.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "commodity")
public class CommodityEntity extends BaseEntity {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @JoinColumn(name = "id")
    private String id;

    @JoinColumn(name = "type")
    private String type;

    @JoinColumn(name = "name")
    private String name;

    @JoinColumn(name = "sales_sum")
    private String salesSum;

    @JoinColumn(name = "price")
    private BigDecimal price;

    @JoinColumn(name = "original_price")
    private BigDecimal originalPrice;

    @JoinColumn(name = "cost_price")
    private BigDecimal costPrice;

    @JoinColumn(name = "imgs")
    private String imgs;


}
