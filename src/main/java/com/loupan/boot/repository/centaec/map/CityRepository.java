package com.loupan.boot.repository.centaec.map;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.loupan.boot.domain.centaec.map.City;

public interface CityRepository extends JpaRepository<City, Integer>, JpaSpecificationExecutor<City> {

	public List<City> findByProvinceId(Integer provinceId);
	
	public City findByName(String name);
}
