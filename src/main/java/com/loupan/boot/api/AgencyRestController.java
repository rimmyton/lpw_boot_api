package com.loupan.boot.api;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.loupan.boot.domain.centaec.map.ViewAreaMap;
import com.loupan.boot.domain.union.LdAgency;
import com.loupan.boot.dto.AgencyDto;
import com.loupan.boot.dto.AgencyLineDto;
import com.loupan.boot.dto.DepartmentLineDto;
import com.loupan.boot.dto.Feedback;
import com.loupan.boot.dto.MapDto;
import com.loupan.boot.service.agency.LdAgencyService;
import com.loupan.boot.service.map.MapService;


@RestController
public class AgencyRestController {
	
	@Autowired
	private LdAgencyService ldAgencyService;
	@Autowired
	private MapService mapService;

	@RequestMapping(value = "/api/agencys/company", produces = MediaTypes.JSON_UTF_8)
	public List<AgencyLineDto> companyList(@RequestParam(value = "page", defaultValue = "1") Integer page,
	        @RequestParam(value = "size", defaultValue = "15") Integer size,HttpServletRequest request) {
		Sort sort = new Sort(Direction.DESC, "id");
	    Pageable pageable = new PageRequest(page-1, size, sort);
	    
	    Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, Servlets.SEARCH_PRE);
	    Iterable<LdAgency> ldAgencys = ldAgencyService.findCompanyAll(searchParams,pageable);
	    
	    List<AgencyLineDto> agencyLineDtos = BeanMapper.mapList(ldAgencys, LdAgency.class,AgencyLineDto.class);
	    for (AgencyLineDto agencyLineDto : agencyLineDtos) {
	    	long countDepartmentAll = ldAgencyService.countDepartmentAll(agencyLineDto.id);
	    	agencyLineDto.departmentCount = countDepartmentAll;
		}
	    
	    return  agencyLineDtos;
	}
	
	@RequestMapping(value = "/api/agencys/department", produces = MediaTypes.JSON_UTF_8)
	public List<DepartmentLineDto> departmentList(@RequestParam(value = "page", defaultValue = "0") Integer page,
	        @RequestParam(value = "size", defaultValue = "15") Integer size,@RequestParam(value="companyId") String companyId,
	        HttpServletRequest request) {
		Sort sort = new Sort(Direction.DESC, "id");
	    Pageable pageable = new PageRequest(page, size, sort);
	    Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, Servlets.SEARCH_PRE);
	    
	    Iterable<LdAgency> ldAgencys = ldAgencyService.findDepartmentAll(searchParams,companyId,pageable);
	    List<DepartmentLineDto> departmentLineDtos = BeanMapper.mapList(ldAgencys, LdAgency.class,DepartmentLineDto.class);
	    
	    for (DepartmentLineDto departmentLineDto : departmentLineDtos) {
	    	if(departmentLineDto.areaId!=null){
	    		ViewAreaMap mapDto = mapService.getMapDto(departmentLineDto.areaId);
	    		if(mapDto!=null){
	    			departmentLineDto.provinceName = mapDto.getProvinceName();
	    			departmentLineDto.cityName = mapDto.getCityName();
	    			departmentLineDto.areaName = mapDto.getAreaName();
	    		}
	    	}
		}
	    return  departmentLineDtos;
	}
	
	@RequestMapping(value = "/api/companyTotal", produces = MediaTypes.JSON_UTF_8)
	public Map<String,Object> agencysTotal() {
		long countCompanyAll = ldAgencyService.countCompanyAll();
		long countDepartmentAll = ldAgencyService.countDepartmentAll();
		long countAgentAll = 0;
		Map<String,Object> map = new HashMap<>();
		map.put("countCompanyAll", countCompanyAll);
		map.put("countDepartmentAll", countDepartmentAll);
		map.put("countAgentAll", countAgentAll);
		return map;
	}
	
	@RequestMapping(value = "/api/departmentTotal", produces = MediaTypes.JSON_UTF_8)
	public Map<String,Object> departmentTotal(@RequestParam(value="companyId") long companyId) {
		long countDepartmentAll = ldAgencyService.countDepartmentAll(companyId);
		long countAgentAll = 0;
		Map<String,Object> map = new HashMap<>();
		map.put("countDepartmentAll", countDepartmentAll);
		map.put("countAgentAll", countAgentAll);
		return map;
	} 
	
	@RequestMapping(value = "/api/agency/{id}")
	public AgencyDto getAgency(@PathVariable("id") Integer id) {
		LdAgency agency = ldAgencyService.findOne(id);
		AgencyDto agencyDto = BeanMapper.map(agency, AgencyDto.class);
		
		Integer areaId = agency.getAreaId();
		if(areaId!=null){
			ViewAreaMap viewAreaMap = mapService.getMapDto(areaId);
			MapDto mapDto= BeanMapper.map(viewAreaMap, MapDto.class);
			agencyDto.setAreaName(mapDto.getAreaName());
			agencyDto.setProvinceName(mapDto.getProvinceName());
			agencyDto.setCityName(mapDto.getCityName());
		}
		if(agency.getParentAgencyId()!=null){
			LdAgency parentAgency = ldAgencyService.findOne(agency.getParentAgencyId());
			if(parentAgency!=null){
				agencyDto.setParentAgencyName(parentAgency.getName());
			}
		}
		
		return agencyDto;
	}
	
	@RequestMapping(value = "/api/agency/saveCompany", method = RequestMethod.POST,produces = MediaTypes.JSON_UTF_8)
	public Object saveCompany(AgencyDto agencyDto) {
		LdAgency ldAgency = BeanMapper.map(agencyDto, LdAgency.class);
		try {
			if(ldAgency.getId()==null){
				ldAgencyService.createCompany(ldAgency);
			}else{
				ldAgencyService.modifyLdAgency(ldAgency);
			}
			
		} catch (Exception e) {
			return new Feedback(false,e.getMessage());
		}
		return new Feedback();
	}
	
	@RequestMapping(value = "/api/agency/saveDepartment", method = RequestMethod.POST,produces = MediaTypes.JSON_UTF_8)
	public Object saveDepartment(AgencyDto agencyDto) {
		LdAgency ldAgency = BeanMapper.map(agencyDto, LdAgency.class);
		try {
			if(ldAgency.getId()==null){
				ldAgencyService.createDepartment(ldAgency);
			}else{
				ldAgencyService.modifyLdAgency(ldAgency);
			}
			
		} catch (Exception e) {
			return new Feedback(false,e.getMessage());
		}
		return new Feedback();
	}
}
