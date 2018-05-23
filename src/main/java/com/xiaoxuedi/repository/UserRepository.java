package com.xiaoxuedi.repository;

import com.xiaoxuedi.entity.UsersEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, String> {
	UsersEntity findByMobile(String mobile);

	@Modifying
	@Query("update UsersEntity set invitationCount = invitationCount + 1 where invitationCode = ?1")
	void addInvitationCount(String code);

	@Query("select u from UsersEntity u where id = ?#{principal.id}")
	UsersEntity getCurrentUser();
}