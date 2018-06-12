package com.xiaoxuedi.repository;

import com.xiaoxuedi.entity.BusinessUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessUsersRepository extends JpaRepository<BusinessUsersEntity, String> {

    List<BusinessUsersEntity> findAllBySchoolId(String schoolId);
}