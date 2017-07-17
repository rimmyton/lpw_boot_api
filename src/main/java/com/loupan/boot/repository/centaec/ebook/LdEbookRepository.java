package com.loupan.boot.repository.centaec.ebook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.loupan.boot.domain.centaec.ebook.LdEbook;

public interface LdEbookRepository extends JpaRepository<LdEbook, Integer>, JpaSpecificationExecutor<LdEbook> {
	
}
