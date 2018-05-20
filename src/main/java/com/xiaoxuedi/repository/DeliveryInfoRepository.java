package com.xiaoxuedi.repository;

import com.xiaoxuedi.entity.DeliveryInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryInfoRepository extends JpaRepository<DeliveryInfoEntity, String> {
}
