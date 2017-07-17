package com.loupan.boot.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.loupan.boot.base.BeanMapper;
import com.loupan.boot.base.MediaTypes;
import com.loupan.boot.base.Servlets;
import com.loupan.boot.domain.centaec.ebook.LdEbook;
import com.loupan.boot.domain.centaec.ebook.LdEbookCategory;
import com.loupan.boot.dto.EbookCategoryDto;
import com.loupan.boot.dto.EbookDto;
import com.loupan.boot.dto.Feedback;
import com.loupan.boot.service.ebook.LdEbookService;

@RestController
public class EbookController {
	
	@Autowired
	private LdEbookService ldEbookService ;

	@RequestMapping(value = "/api/ebook/list", produces = MediaTypes.JSON_UTF_8)
	public  Page<LdEbook> ldEbookList(@RequestParam(value = "page", defaultValue = "1") Integer page,
	        @RequestParam(value = "size", defaultValue = "15") Integer size,HttpServletRequest request) {
		Sort sort = new Sort(Direction.DESC, "id");
	    Pageable pageable = new PageRequest(page-1, size, sort);
	    Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, Servlets.SEARCH_PRE);
	    Page<LdEbook> pageInfo = ldEbookService.findLdEbookAll(searchParams,pageable);
	    return pageInfo;
	}
	
	@RequestMapping(value = "/api/ebook/allList", produces = MediaTypes.JSON_UTF_8)
	public Iterable<EbookCategoryDto> ebookCategoryDtoList(HttpServletRequest request) {
	    Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, Servlets.SEARCH_PRE);
	    Iterable<LdEbook> pageInfo = ldEbookService.findLdEbookAll(searchParams);
	    
	    List<EbookCategoryDto> ebookCategoryDtos = new ArrayList<>();
	    EbookCategoryDto ebookCategoryDto = new EbookCategoryDto();
	    for (LdEbook ldEbook : pageInfo) {
	    	LdEbookCategory ebookCategory = ldEbook.getLdEbookCategory();
	    	if(!ebookCategory.getId().equals(ebookCategoryDto.getId())){
	    		ebookCategoryDto = BeanMapper.map(ebookCategory, EbookCategoryDto.class);
	    		ebookCategoryDtos.add(ebookCategoryDto);
	    	}
	    	EbookDto ebookDto = BeanMapper.map(ldEbook,EbookDto.class);
    		ebookCategoryDto.getEbookDtos().add(ebookDto);
		}
	    
	    return ebookCategoryDtos;
	}
	
	@RequestMapping(value = "/api/ebookCategory/list", produces = MediaTypes.JSON_UTF_8)
	public Iterable<LdEbookCategory> ldEbookCategoryList() {
		Iterable<LdEbookCategory> findLdEbookCategoryAll = ldEbookService.findLdEbookCategoryAll();
		return findLdEbookCategoryAll;
	}
	
	@RequestMapping(value = "/api/ebook/{id}")
	public LdEbook getEbook(@PathVariable("id") Integer id) {
		LdEbook entity = ldEbookService.findLdEbook(id);
		return entity;
	}
	
	@RequestMapping(value = "/api/ebook/del/{id}",  method = RequestMethod.POST,produces = MediaTypes.JSON_UTF_8)
	public Feedback delLdEbook(@PathVariable("id") Integer id) {
		try {
			ldEbookService.delLdEbook(id);
		} catch (ServiceException e) {
			return new Feedback(false,e.getMessage());
		}
		return new Feedback();
	}
	
	@RequestMapping(value = "/api/ebook/save", produces = MediaTypes.JSON_UTF_8)
	public Feedback saveLdEbook(String data) {
		LdEbook entity = JSONObject.parseObject(data, LdEbook.class);
		try {
			if(entity.getId()==null){
				ldEbookService.createLdEbook(entity);
			}else{
				ldEbookService.modifyLdEbook(entity);
			}
		} catch (ServiceException e) {
			return new Feedback(false,e.getMessage());
		}
		return new Feedback();
	}
	
	@RequestMapping(value = "/api/ebookCategory/save", produces = MediaTypes.JSON_UTF_8)
	public Feedback saveLdEbookCategory(LdEbookCategory entity) {
		try {
			if(entity.getId()==null){
				ldEbookService.createLdEbookCategory(entity);
			}else{
				ldEbookService.modifyLdEbookCategory(entity);
			}
		} catch (ServiceException e) {
			return new Feedback(false,e.getMessage());
		}catch (Exception e) {
			return new Feedback(false,"保存失败，名称:"+entity.getName()+"已存在！");
		}
		return new Feedback();
	}
	
	@RequestMapping(value = "/api/ebookCategory/del/{id}",  method = RequestMethod.POST,produces = MediaTypes.JSON_UTF_8)
	public Feedback delLdEbookCategory(@PathVariable("id") Integer id) {
		try {
			ldEbookService.delLdEbookCategory(id);
		} catch (ServiceException e) {
			return new Feedback(false,e.getMessage());
		}
		return new Feedback();
	}
	
}
