package com.xiaoxuedi.repository;

import com.xiaoxuedi.entity.Mission;
import com.xiaoxuedi.entity.Order;
import com.xiaoxuedi.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String>
{
	List<Order> findAllByUser(User user, Pageable pageable);

	void deleteByUserAndMission(User user, Mission mission);

	Order findByUserAndMission(User user, Mission mission);
}
