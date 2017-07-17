package com.loupan.boot.repository.centaec.estate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.loupan.boot.domain.centaec.estate.LdEstate;

public interface LdEstateRepository extends JpaRepository<LdEstate, Integer>, JpaSpecificationExecutor<LdEstate> {

	@Modifying
	@Transactional
	@Query("update LdEstate set sortNum = sortNum+1 where sortNum >= ?1 and sortNum < ?2")
	public void updateLdEstateSortNum(int begin,int end);
	
}
