package com.loupan.boot.repository.centaec.estate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.loupan.boot.domain.centaec.estate.LdEstateAdviser;

public interface LdEstateAdviserRepository extends JpaRepository<LdEstateAdviser, Integer>, JpaSpecificationExecutor<LdEstateAdviser> {

	List<LdEstateAdviser> findByEstId(int estId);
}
