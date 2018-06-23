package com.xiaoxuedi.repository;

import com.xiaoxuedi.entity.MissionEntity;
import com.xiaoxuedi.entity.SchoolEntity;
import com.xiaoxuedi.entity.UsersEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<MissionEntity, String> {
	
	 MissionEntity findByMissionNo(String missionNo);
    MissionEntity findById(String id);
	
    List<MissionEntity> findAllByUserAndStatusIn(UsersEntity user, MissionEntity.Status[] status, Pageable pageable);

//    List<MissionEntity> findAllByAcceptUserAndStatusIn(UsersEntity user, MissionEntity.Status[] status, Pageable pageable);

//    List<MissionEntity> findAllByUserSchoolAndStatusIn(SchoolEntity school, MissionEntity.Status[] status, Pageable pageable);
    
    List<MissionEntity> findAllByUser(UsersEntity user, Pageable pageable);
}
