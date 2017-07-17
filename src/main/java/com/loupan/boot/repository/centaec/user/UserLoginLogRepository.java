package com.loupan.boot.repository.centaec.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.loupan.boot.domain.centaec.user.UserLoginLog;

public interface UserLoginLogRepository extends JpaRepository<UserLoginLog, Integer>, JpaSpecificationExecutor<UserLoginLog> {
	
	@Modifying
	@Transactional
	@Query(nativeQuery=true,value="update user_union.lp_users set last_time=?1 where id = ?2")
	public void updateLastSignonTime(Integer date ,int userId);
}
