package com.loupan.boot.api;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.loupan.boot.base.MediaTypes;
import com.loupan.boot.domain.centaec.estate.LdEstateAdviser;
import com.loupan.boot.dto.Feedback;
import com.loupan.boot.service.estate.LdEstateService;

@RestController
public class EstateController {
	
	@Autowired
	private LdEstateService ldEstateService;
	
	
	//更新位置
	@RequestMapping(value = "/api/estate/updateSortNum", produces = MediaTypes.JSON_UTF_8)
	public Feedback updateSortNum(int estId,int sortNum){
		try {
			ldEstateService.updateSortNum(estId, sortNum);
		} catch (Exception e) {
			return new Feedback(false,e.getMessage());
		}
		return new Feedback();
	}
	//移动至第几位
	@RequestMapping(value = "/api/estate/moveTo", produces = MediaTypes.JSON_UTF_8)
	public Feedback estMoveTo(int estId,int toNum){
		try {
			ldEstateService.estMoveTo(estId, toNum);
		} catch (Exception e) {
			return new Feedback(false,e.getMessage());
		}
		return new Feedback();
	}
	//上移
	@RequestMapping(value = "/api/estate/estMoveUp", produces = MediaTypes.JSON_UTF_8)
	public Feedback estMoveUp(Integer cityId,int estId){
		try {
			ldEstateService.estMoveUp(cityId,estId);
		} catch (Exception e) {
			return new Feedback(false,e.getMessage());
		}
		return new Feedback();
	}
	//移动至第几位
	@RequestMapping(value = "/api/estate/estMoveDown", produces = MediaTypes.JSON_UTF_8)
	public Feedback estMoveDown(Integer cityId,int estId){
		try {
			ldEstateService.estMoveDown(cityId,estId);
		} catch (Exception e) {
			return new Feedback(false,e.getMessage());
		}
		return new Feedback();
	}
	
	@RequestMapping(value = "/api/agency/getLdEstateAdvisers",  produces = MediaTypes.JSON_UTF_8)
	public List<LdEstateAdviser> getLdEstateAdvisers(Integer estId) {
		List<LdEstateAdviser> ldEstateAdvisers = ldEstateService.getLdEstateAdvisers(estId);
		return ldEstateAdvisers;
	}
	
	@RequestMapping(value = "/api/agency/getLdEstateAdviserById",  produces = MediaTypes.JSON_UTF_8)
	public LdEstateAdviser getLdEstateAdviserById(Integer id) {
		LdEstateAdviser ldEstateAdviser = ldEstateService.getLdEstateAdvisersById(id);
		return ldEstateAdviser;
	}
	
	@RequestMapping(value = "/api/agency/saveLdEstateAdviser",  method = RequestMethod.POST,produces = MediaTypes.JSON_UTF_8)
	public Feedback saveLdEstateAdviser(LdEstateAdviser ldEstateAdviser) {
		try {
			ldEstateService.saveLdEstateAdviser(ldEstateAdviser);
		} catch (Exception e) {
			return new Feedback(false,e.getMessage());
		}
		return new Feedback();
	}
	
	@RequestMapping(value = "/api/agency/delLdEstateAdviser",  method = RequestMethod.POST,produces = MediaTypes.JSON_UTF_8)
	public Feedback delLdEstateAdviser(int id) {
		try {
			ldEstateService.delLdEstateAdviser(id);
		} catch (Exception e) {
			return new Feedback(false,e.getMessage());
		}
		return new Feedback();
	}
}
