package com.loupan.boot.service.estate;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loupan.boot.base.BeanMapper;
import com.loupan.boot.domain.centaec.estate.LdEstate;
import com.loupan.boot.domain.centaec.estate.LdEstateAdviser;
import com.loupan.boot.repository.centaec.estate.LdEstateAdviserRepository;
import com.loupan.boot.repository.centaec.estate.LdEstateDao;
import com.loupan.boot.repository.centaec.estate.LdEstateRepository;


@Service
public class LdEstateService{
	
	private static Logger logger = LoggerFactory.getLogger(LdEstateService.class);

	@Autowired
	private LdEstateRepository ldEstateRepository;
	@Autowired
	private LdEstateAdviserRepository ldEstateAdviserRepository;
	@Autowired
	private LdEstateDao ldEstateDao;
	
	
	public LdEstate getLdEstate(int id){
		return ldEstateRepository.findOne(id);
	}
	
	@Transactional
	public void updateSortNum(int estId,int sortNum){
		LdEstate ldEstate = this.getLdEstate(estId);
		ldEstate.setSortNum(sortNum);
		ldEstate.setModDate(new Date());
		ldEstateRepository.save(ldEstate);
	}
	
	@Transactional
	public void estMoveTo(int estId,int toNum){
		LdEstate ldEstate = this.getLdEstate(estId);
		Integer sortNum = ldEstate.getSortNum();
		
		if(sortNum==null){
			LdEstate lastLdEstate = this.getLastLdEstate();
			if(lastLdEstate==null){
				ldEstate.setSortNum(1);
			}else{
				ldEstate.setSortNum(lastLdEstate.getSortNum()+1);
			}
		}
		sortNum = ldEstate.getSortNum();
		ldEstate.setSortNum(toNum);
		
		if(sortNum>toNum){
			this.updateLdEstateSortNum(toNum, sortNum);	
		}else{
			this.updateLdEstateSortNum(sortNum, toNum);	
		}
		ldEstateRepository.save(ldEstate);
	}
	
	@Transactional
	public void estMoveUp(Integer cityId,int estId){
		LdEstate ldEstate = this.getLdEstate(estId);
		Integer sortNum = ldEstate.getSortNum();
		
		if(sortNum==null){
			LdEstate lastLdEstate = this.getLastLdEstate();
			if(lastLdEstate==null){
				ldEstate.setSortNum(1);
			}else{
				ldEstate.setSortNum(lastLdEstate.getSortNum()+1);
			}
		}
		if(cityId==null){
			cityId = 440100;
		}
		Map<String, Object> ldEstate2 = ldEstateDao.findTop1ByCityIdGreaterThanSortNum(cityId, sortNum);
		if(ldEstate2!=null){
			Integer id = Integer.valueOf(ldEstate2.get("id").toString());
			LdEstate ldEstate3 = this.getLdEstate(id);
			Integer sortNum2 = ldEstate.getSortNum();
			ldEstate.setSortNum(ldEstate3.getSortNum());
			ldEstate3.setSortNum(sortNum2);
			ldEstateRepository.save(ldEstate);
			ldEstateRepository.save(ldEstate3);
		}
	}
	
	@Transactional
	public void estMoveDown(Integer cityId,int estId){
		LdEstate ldEstate = this.getLdEstate(estId);
		Integer sortNum = ldEstate.getSortNum();
		
		if(sortNum==null){
			LdEstate lastLdEstate = this.getLastLdEstate();
			if(lastLdEstate==null){
				ldEstate.setSortNum(1);
			}else{
				ldEstate.setSortNum(lastLdEstate.getSortNum()+1);
			}
		}
		if(cityId==null){
			cityId = 440100;
		}
		Map<String, Object> ldEstate2 = ldEstateDao.findTop1ByCityIdLessThanSortNum(cityId, sortNum);
		if(ldEstate2!=null){
			Integer id = Integer.valueOf(ldEstate2.get("id").toString());
			LdEstate ldEstate3 = this.getLdEstate(id);
			Integer sortNum2 = ldEstate.getSortNum();
			ldEstate.setSortNum(ldEstate3.getSortNum());
			ldEstate3.setSortNum(sortNum2);
			ldEstateRepository.save(ldEstate);
			ldEstateRepository.save(ldEstate3);
		}
	}
	
	/**
	 * 对begin~end范围内的顺序号+1
	 * @param begin 包含开始位置
	 * @param end 不包括结束位置
	 */
	public void updateLdEstateSortNum(int begin,int end){
		ldEstateRepository.updateLdEstateSortNum(begin, end);
	}
	
	
	
	public LdEstate getLastLdEstate(){
		Page<LdEstate> findAll = ldEstateRepository.findAll(new PageRequest(0, 1, new Sort(Direction.DESC, "sortNum")));
		if(findAll.getSize()>0){
			return findAll.getContent().get(0);
		}
		return null;
	}
	
	//置业顾问列表
	public List<LdEstateAdviser> getLdEstateAdvisers(int estId){
		return  ldEstateAdviserRepository.findByEstId(estId);
	}
	
	public LdEstateAdviser getLdEstateAdvisersById(int id){
		return  ldEstateAdviserRepository.findOne(id);
	}
	
	//保存置业顾问
	@Transactional
	public void saveLdEstateAdviser(LdEstateAdviser ldEstateAdviser){
		LdEstateAdviser orginalEntity =new LdEstateAdviser(); 
		if(ldEstateAdviser.getId()!=null){
			orginalEntity = ldEstateAdviserRepository.findOne(ldEstateAdviser.getId());
		}
		BeanMapper.copyProprty(orginalEntity, ldEstateAdviser);
		ldEstateAdviserRepository.save(orginalEntity);
	}
	
	//删除置业顾问
	@Transactional
	public void delLdEstateAdviser(int id){
		ldEstateAdviserRepository.delete(id);
	}
	
	public List<Map<String,Object>> findEstateComment(String tableName){
		return ldEstateDao.findAllCommon(tableName);
	}
	
	public Map<String,Object> findEstateTablteComment(String tableName){
		return ldEstateDao.findTableCommon(tableName);
	}
}
