package com.xiaoxuedi.repository;

import com.xiaoxuedi.entity.DeliveryUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryUsersRepository extends JpaRepository<DeliveryUsersEntity, String> {
}
