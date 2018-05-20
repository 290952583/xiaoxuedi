package com.xiaoxuedi.repository;

import com.xiaoxuedi.entity.OrderCommodityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCommodityRepository extends JpaRepository<OrderCommodityEntity, String> {
}
