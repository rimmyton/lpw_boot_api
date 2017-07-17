package com.loupan.boot.service.agency;


import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loupan.boot.base.BeanMapper;
import com.loupan.boot.base.DynamicSpecifications;
import com.loupan.boot.base.SearchFilter;
import com.loupan.boot.base.SearchFilter.Operator;
import com.loupan.boot.domain.union.LdAgency;
import com.loupan.boot.repository.union.agency.LdAgencyRepository;


@Service
public class LdAgencyService {

	final int companyLevel = 1;//级别（1公司、2部门）
	final int departmentLevel = 2;
	
	private static Logger logger = LoggerFactory.getLogger(LdAgencyService.class);

	@Autowired
	private LdAgencyRepository ldAgencyRepository;

	public LdAgency findOne(Integer id) {
		return ldAgencyRepository.findOne(id);
	}
	
	public Iterable<LdAgency> findCompanyAll(Map<String, Object> searchParams,Pageable pageable) {
		Set<SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.add(new SearchFilter("level", Operator.EQ, companyLevel));
		Specification<LdAgency> spec = DynamicSpecifications.bySearchFilter(filters, LdAgency.class);
		return ldAgencyRepository.findAll(spec, pageable);
	}
	
	public Iterable<LdAgency> findDepartmentAll(Map<String, Object> searchParams,String companyId,Pageable pageable) {
		Set<SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.add(new SearchFilter("level", Operator.EQ, departmentLevel));
		filters.add(new SearchFilter("parentAgencyId", Operator.EQ, companyId));
		Specification<LdAgency> spec = DynamicSpecifications.bySearchFilter(filters, LdAgency.class);
		return ldAgencyRepository.findAll(spec, pageable);
	}
	
	public long countCompanyAll(){
		Set<SearchFilter> filters = new  HashSet<>();
		filters.add(new SearchFilter("level", Operator.EQ, companyLevel));
		Specification<LdAgency> spec = DynamicSpecifications.bySearchFilter(filters, LdAgency.class);
		return ldAgencyRepository.count(spec);
	}
	
	public long countDepartmentAll(){
		Set<SearchFilter> filters = new  HashSet<>();
		filters.add(new SearchFilter("level", Operator.EQ, departmentLevel));
		Specification<LdAgency> spec = DynamicSpecifications.bySearchFilter(filters, LdAgency.class);
		return ldAgencyRepository.count(spec);
	}
	
	public long countDepartmentAll(long companyId){
		Set<SearchFilter> filters = new  HashSet<>();
		filters.add(new SearchFilter("level", Operator.EQ, departmentLevel));
		filters.add(new SearchFilter("parentAgencyId", Operator.EQ, companyId));
		Specification<LdAgency> spec = DynamicSpecifications.bySearchFilter(filters, LdAgency.class);
		return ldAgencyRepository.count(spec);
	}
	
	@Transactional
	public void createCompany(LdAgency ldAgency) throws Exception{
		ldAgency.setCreateTime(new Date());
		ldAgency.setTimestamp(new Date());
		ldAgency.setLevel(1);
		LdAgency findByName = ldAgencyRepository.findByName(ldAgency.getName());
		if(findByName!=null){
			throw new ServiceException("公司名称已存在");
		}
		ldAgencyRepository.save(ldAgency);
	}
	
	@Transactional
	public void createDepartment(LdAgency ldAgency) throws Exception{
		ldAgency.setCreateTime(new Date());
		ldAgency.setTimestamp(new Date());
		ldAgency.setLevel(2);
		LdAgency findByName = ldAgencyRepository.findByName(ldAgency.getName());
		if(findByName!=null){
			throw new ServiceException("门店名称已存在");
		}
		ldAgencyRepository.save(ldAgency);
	}
	
	@Transactional
	public void modifyLdAgency(LdAgency ldAgency) throws Exception{
		LdAgency orginalEntity = ldAgencyRepository.findOne(ldAgency.getId());
		LdAgency findByName = ldAgencyRepository.findByName(ldAgency.getName());
		if(findByName!=null && !orginalEntity.getId().equals(findByName.getId())){
			throw new ServiceException("公司名称已存在");
		}
		BeanMapper.copyProprty(orginalEntity, ldAgency);
		ldAgencyRepository.save(orginalEntity);
	}
	
	
}
