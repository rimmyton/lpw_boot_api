package com.loupan.boot.service.log;

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loupan.boot.base.DynamicSpecifications;
import com.loupan.boot.base.SearchFilter;
import com.loupan.boot.base.SearchFilter.Operator;
import com.loupan.boot.domain.centaec.log.LdTbLogContent;
import com.loupan.boot.repository.centaec.log.LdLogContentRepository;


@Service
public class LdLogContentService {

	private static Logger logger = LoggerFactory.getLogger(LdLogContentService.class);
	
	@Autowired
	private LdLogContentRepository ldLogContentRepository;
	
	@Transactional
	public void saveOperationContent(LdTbLogContent ldTbLogContent) throws Exception{
		ldLogContentRepository.save(ldTbLogContent);
	}
	
	@Transactional
	public void saveOperationContentAll(Iterable<LdTbLogContent> ldTbLogContent) throws Exception{
		ldLogContentRepository.save(ldTbLogContent);
	}
	
	
	public Page<LdTbLogContent> findLdTbLogContentAll(Map<String, Object> searchParams,Pageable pageable,String pid) {
		Set<SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.add(new SearchFilter("ldTbLog.tbid", Operator.EQ, pid));
		Specification<LdTbLogContent> spec = DynamicSpecifications.bySearchFilter(filters, LdTbLogContent.class);
		return ldLogContentRepository.findAll(spec, pageable);
	}
}
