package com.xiaoxuedi.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class OrderCommodityEntity {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @JoinColumn(name = "id")
    private String id;

    @JoinColumn(name = "order_id")
    private String orderId;

    @JoinColumn(name = "commodity_id")
    private String commodityId;

    @JoinColumn(name = "commodity_name")
    private String commodityName;

    @JoinColumn(name = "commodity_sum")
    private int commoditySum;

    @JoinColumn(name = "commodity_amount_sum")
    private BigDecimal commodityAmountSum;


}
