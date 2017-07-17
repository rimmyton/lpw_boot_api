package com.loupan.boot.repository.union.agency;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.loupan.boot.domain.union.LdAgency;


public interface LdAgencyRepository extends JpaRepository<LdAgency, Integer>, JpaSpecificationExecutor<LdAgency> {

	List<LdAgency> findByLevel(int level,Pageable pageable);
	
	LdAgency findByName(String name);
	
	
}
