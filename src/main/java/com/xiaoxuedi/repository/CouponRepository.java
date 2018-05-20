package com.xiaoxuedi.repository;

import com.xiaoxuedi.entity.CouponEntity;
import com.xiaoxuedi.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<CouponEntity, String> {
}
