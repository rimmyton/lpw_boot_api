package com.loupan.boot.service.map;


import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loupan.boot.base.DynamicSpecifications;
import com.loupan.boot.base.SearchFilter;
import com.loupan.boot.domain.centaec.map.Area;
import com.loupan.boot.domain.centaec.map.City;
import com.loupan.boot.domain.centaec.map.Province;
import com.loupan.boot.domain.centaec.map.Region;
import com.loupan.boot.domain.centaec.map.ViewAreaMap;
import com.loupan.boot.repository.centaec.map.AreaRepository;
import com.loupan.boot.repository.centaec.map.CityRepository;
import com.loupan.boot.repository.centaec.map.ProvinceRepository;
import com.loupan.boot.repository.centaec.map.RegionRepository;
import com.loupan.boot.repository.centaec.map.ViewAreaMapRepository;


@Service
public class MapService {


	@Autowired
	private ProvinceRepository provinceRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private AreaRepository areaRepository;
	@Autowired
	private ViewAreaMapRepository viewAreaMapRepository;
	@Autowired
	private RegionRepository regionRepository;
	

	//获得省列表
	public Map<Integer,String> getProvinces(){
		List<Province> all = provinceRepository.findAll(new Sort(Direction.ASC, "id"));
		Map<Integer,String> m = new LinkedHashMap<>();
		for (Province province : all) {
			m.put(province.getId(), province.getName());
		}
		return m;
	}
	
	//获得市列表
	public Map<Integer,String> getCitys(int provinceId){
		List<City> all = cityRepository.findByProvinceId(provinceId);
		Map<Integer,String> m = new LinkedHashMap<>();
		for (City city : all) {
			m.put(city.getId(), city.getName());
		}
		return m;
	}
	
	//获得区列表
	public Map<Integer,String> getAreas(int cityId){
		List<Area> all = areaRepository.findByCityId(cityId);
		Map<Integer,String> m = new LinkedHashMap<>();
		for (Area area : all) {
			m.put(area.getId(), area.getName());
		}
		return m;
	}
	
	//获得板块列表
	public Map<Integer,String> getRegions(int areaId){
		List<Region> all = regionRepository.findByAreaId(areaId);
		Map<Integer,String> m = new LinkedHashMap<>();
		for (Region region : all) {
			m.put(region.getId(), region.getName());
		}
		return m;
	}
	
	//获得省市区地址
	@Transactional
	public ViewAreaMap getMapDto(int areaId){
		return viewAreaMapRepository.findOne(areaId);
	}
	
	public City getCityByName(String cityName){
		return cityRepository.findByName(cityName);
	}
	
	public Page<Region> findRegionAll(Map<String, Object> searchParams,Pageable pageable){
		Set<SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<Region> spec = DynamicSpecifications.bySearchFilter(filters, Region.class);
		return regionRepository.findAll(spec, pageable);
	}
	
	public Region findRegionOne(int id){
		return regionRepository.findOne(id);
	}
	
	public void addRegion(String name,int areaId){
		Region entity = new Region();
		entity.setAreaId(areaId);
		entity.setName(name);
		entity.setCreateTime(new Date());
		entity.setTimestamp(new Date());
		regionRepository.save(entity);
	}
	
	public void modifyRegion(int id,String name,int areaId){
		Region findOne = regionRepository.findOne(id);
		findOne.setName(name);
		findOne.setAreaId(areaId);
		regionRepository.save(findOne);
	}
	
	public void delRegion(int id){
		regionRepository.delete(id);
	}
	
}
