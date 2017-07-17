package com.loupan.boot.repository.centaec.ebook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.loupan.boot.domain.centaec.ebook.LdEbookCategory;

public interface LdEbookCategoryRepository extends JpaRepository<LdEbookCategory, Integer>, JpaSpecificationExecutor<LdEbookCategory> {
}
