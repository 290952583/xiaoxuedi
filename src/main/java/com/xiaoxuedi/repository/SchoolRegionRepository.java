package com.xiaoxuedi.repository;

import com.xiaoxuedi.entity.SchoolRegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolRegionRepository extends JpaRepository<SchoolRegionEntity, String>
{
    List<SchoolRegionEntity> findBySchoolId(String schoolId);
}
