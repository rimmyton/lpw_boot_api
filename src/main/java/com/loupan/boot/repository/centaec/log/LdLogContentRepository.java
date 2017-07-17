package com.loupan.boot.repository.centaec.log;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.loupan.boot.domain.centaec.log.LdTbLogContent;


public interface LdLogContentRepository extends JpaRepository<LdTbLogContent, Integer>, JpaSpecificationExecutor<LdTbLogContent>{

	
}
