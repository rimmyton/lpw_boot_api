package com.loupan.boot.repository.centaec.map;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.loupan.boot.domain.centaec.map.Region;

public interface RegionRepository extends JpaRepository<Region, Integer>, JpaSpecificationExecutor<Region> {

	public List<Region> findByAreaId(Integer areaId);
}
