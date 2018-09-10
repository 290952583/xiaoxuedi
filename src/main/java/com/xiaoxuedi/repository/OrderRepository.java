package com.xiaoxuedi.repository;

import com.xiaoxuedi.entity.OrdersEntity;
import com.xiaoxuedi.entity.UsersEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrdersEntity, String> {

    OrdersEntity findByOrderNo(String orderNo);

    OrdersEntity findById(String id);

    List<OrdersEntity> findAllByUser(UsersEntity user, Pageable pageable);

    List<OrdersEntity> findAllByUserAndStatusIn(UsersEntity user, OrdersEntity.Status[] status, Pageable pageable);

    List<OrdersEntity> findAllByUserAndTypeIn(UsersEntity user, OrdersEntity.Type[] types, Pageable pageable);


}
