package com.xiaoxuedi.repository;

import com.xiaoxuedi.entity.AddressEntity;
import com.xiaoxuedi.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, String> {
    List<AddressEntity> findAllByUser(UsersEntity user);

    int countByUser(UsersEntity user);
}
