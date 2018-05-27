package com.xiaoxuedi.repository;

import com.xiaoxuedi.entity.CouponEntity;
import com.xiaoxuedi.entity.UsersEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<CouponEntity, String> {
	
	//查询所有红包信息
	List<CouponEntity> findAllByUser(UsersEntity user);
}
