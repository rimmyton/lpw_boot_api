package com.loupan.boot.api;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loupan.boot.base.BeanMapper;
import com.loupan.boot.base.MediaTypes;
import com.loupan.boot.base.Servlets;
import com.loupan.boot.domain.centaec.map.City;
import com.loupan.boot.domain.centaec.map.Region;
import com.loupan.boot.dto.Feedback;
import com.loupan.boot.dto.RegionDto;
import com.loupan.boot.service.map.MapService;

//Spring Restful MVC Controller的标识, 直接输出内容，不调用template引擎.
@RestController
public class MapRestController {
	
	@Autowired
	private MapService mapService;

	@RequestMapping(value = "/api/map/provinces", produces = MediaTypes.JSON_UTF_8)
	public Map<Integer,String> getProvinces() {
		return mapService.getProvinces();
	}
	
	@RequestMapping(value = "/api/map/citys", produces = MediaTypes.JSON_UTF_8)
	public Map<Integer,String> getCitys(@RequestParam(value="id") int id) {
		return mapService.getCitys(id);
	}
	
	@RequestMapping(value = "/api/map/areas", produces = MediaTypes.JSON_UTF_8)
	public Map<Integer,String> getAreas(@RequestParam(value="id") int id) {
		return mapService.getAreas(id);
	}
	
	@RequestMapping(value = "/api/map/regions", produces = MediaTypes.JSON_UTF_8)
	public Map<Integer,String> getRegions(@RequestParam(value="id") int id) {
		return mapService.getRegions(id);
	}
	
	@RequestMapping(value = "/api/map/citys/{cityName}", produces = MediaTypes.JSON_UTF_8)
	public int getCityIdByName(@PathVariable("cityName") String cityName) {
		
		City city = mapService.getCityByName(cityName);
		if(city!=null){
			return city.getId();
		}
		return 0; 
	}
	
	
	
	//区域板块列表
	@RequestMapping(value = "/api/map/region", produces = MediaTypes.JSON_UTF_8)
	public Page<RegionDto> regionList(@RequestParam(value = "page", defaultValue = "1") Integer page,
	        @RequestParam(value = "size", defaultValue = "15") Integer size,HttpServletRequest request) {
		Sort sort = new Sort(Direction.DESC, "id");
	    Pageable pageable = new PageRequest(page-1, size, sort);
	    Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, Servlets.SEARCH_PRE);
	    Page<Region> regions = mapService.findRegionAll(searchParams, pageable);
	    Page<RegionDto> regionPage = new PageImpl<>(BeanMapper.mapList(regions, Region.class,RegionDto.class), pageable, regions.getTotalElements());
	    return  regionPage;
	}
	
	@RequestMapping(value = "/api/map/saveRegion", method = RequestMethod.POST,produces = MediaTypes.JSON_UTF_8)
	public Object saveRegion(Integer id,String name,int areaId) {
		try {
			if(id==null){
				mapService.addRegion(name, areaId);
			}else{
				mapService.modifyRegion(id,name,areaId);
			}
			
		} catch (Exception e) {
			return new Feedback(false,e.getMessage());
		}
		return new Feedback();
	}
	
	@RequestMapping(value = "/api/map/region/{id}")
	public RegionDto getAgency(@PathVariable("id") int id) {
		Region region = mapService.findRegionOne(id);
		RegionDto regionDto = BeanMapper.map(region, RegionDto.class);
		return regionDto;
	}
	
	@RequestMapping(value = "/api/map/region/{id}/del")
	public Feedback delAgency(@PathVariable("id") int id) {
		try {
			mapService.delRegion(id);
		} catch (Exception e) {
			return new Feedback(false,"删除失败,系统异常！");
		}
		return new Feedback(true,"删除成功");
	}
}
