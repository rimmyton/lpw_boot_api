package com.loupan.boot.service.ebook;


import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.loupan.boot.base.BeanMapper;
import com.loupan.boot.base.DynamicSpecifications;
import com.loupan.boot.base.SearchFilter;
import com.loupan.boot.base.SearchFilter.Operator;
import com.loupan.boot.domain.centaec.ebook.LdEbook;
import com.loupan.boot.domain.centaec.ebook.LdEbookCategory;
import com.loupan.boot.repository.centaec.ebook.LdEbookCategoryRepository;
import com.loupan.boot.repository.centaec.ebook.LdEbookRepository;


@Service
public class LdEbookService {
	@Autowired
	private LdEbookRepository ldEbookRepository;
	@Autowired
	private LdEbookCategoryRepository ldEbookCategoryRepository;
	
	public Page<LdEbook> findLdEbookAll(Map<String, Object> searchParams,Pageable pageable) {
		Set<SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<LdEbook> spec = DynamicSpecifications.bySearchFilter(filters, LdEbook.class);
		return ldEbookRepository.findAll(spec, pageable);
	}
	
	public Iterable<LdEbook> findLdEbookAll(Map<String, Object> searchParams) {
		Set<SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<LdEbook> spec = DynamicSpecifications.bySearchFilter(filters, LdEbook.class);
		Sort sort = new Sort(Direction.DESC, "ldEbookCategory.name");
		return ldEbookRepository.findAll(spec,sort);
	}
	
	public LdEbook findLdEbook(Integer id) {
		return ldEbookRepository.findOne(id);
	}
	
	public void createLdEbook(LdEbook entity) {
		entity.setTimestamp(new Date());
		ldEbookRepository.save(entity);
	}
	
	public void modifyLdEbook(LdEbook entity){
		LdEbook orginalEntity = ldEbookRepository.findOne(entity.getId());
		if(orginalEntity==null){
			throw new ServiceException("编辑失败!");
		}
		orginalEntity.setTimestamp(new Date());
		BeanMapper.copyProprty(orginalEntity, entity);
		ldEbookRepository.save(orginalEntity);
	}
	
	public void delLdEbook(int id){
		ldEbookRepository.delete(id);
	}
	
	public Iterable<LdEbookCategory> findLdEbookCategoryAll() {
		return ldEbookCategoryRepository.findAll();
	}
	
	public void createLdEbookCategory(LdEbookCategory entity) {
		ldEbookCategoryRepository.save(entity);
	}
	
	public void modifyLdEbookCategory(LdEbookCategory entity){
		LdEbookCategory orginalEntity = ldEbookCategoryRepository.findOne(entity.getId());
		if(orginalEntity==null){
			throw new ServiceException("编辑失败!");
		}
		BeanMapper.copyProprty(orginalEntity, entity);
		ldEbookCategoryRepository.save(orginalEntity);
	}
	
	public void delLdEbookCategory(int id){
		Set<SearchFilter> filters = new HashSet<>();
		filters.add(new SearchFilter("ldEbookCategory.id", Operator.EQ, id));
		Specification<LdEbook> spec = DynamicSpecifications.bySearchFilter(filters, LdEbook.class);
		long count = ldEbookRepository.count(spec);
		if(count!=0){
			throw new ServiceException("删除失败！该分类已经被使用!");
		}
		ldEbookCategoryRepository.delete(id);
	}
}
