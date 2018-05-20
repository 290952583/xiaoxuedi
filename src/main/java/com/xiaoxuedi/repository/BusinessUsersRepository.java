package com.xiaoxuedi.repository;

import com.xiaoxuedi.entity.BusinessUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessUsersRepository extends JpaRepository<BusinessUsersEntity, String> {
}