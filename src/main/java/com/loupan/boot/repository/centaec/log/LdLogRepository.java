package com.loupan.boot.repository.centaec.log;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.loupan.boot.domain.centaec.log.LdTbLog;

public interface LdLogRepository extends JpaRepository<LdTbLog, Integer>, JpaSpecificationExecutor<LdTbLog>{

	public List<LdTbLog> findByProjectId(int projectId);

}
