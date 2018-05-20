package com.xiaoxuedi.repository;

import com.xiaoxuedi.entity.BusinessUsersEntity;
import com.xiaoxuedi.entity.CommodityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommodityRepository extends JpaRepository<CommodityEntity, String> {
}