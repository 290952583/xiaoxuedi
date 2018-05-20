package com.xiaoxuedi.repository;

import com.xiaoxuedi.entity.SysUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUsersRepository extends JpaRepository<SysUsersEntity, String> {
}
