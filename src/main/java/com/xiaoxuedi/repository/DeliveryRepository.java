package com.xiaoxuedi.repository;

import com.xiaoxuedi.entity.DeliveryEntity;
import com.xiaoxuedi.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<DeliveryEntity, String> {
}
