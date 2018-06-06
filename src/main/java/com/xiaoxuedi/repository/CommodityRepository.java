package com.xiaoxuedi.repository;

import com.xiaoxuedi.entity.CommodityEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommodityRepository extends JpaRepository<CommodityEntity, String> {
	
    List<CommodityEntity> findAllByBusinessId(String businessId);
	
	
}