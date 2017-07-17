package com.loupan.boot.service.user;


import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.loupan.boot.base.BeanMapper;
import com.loupan.boot.base.DynamicSpecifications;
import com.loupan.boot.base.SearchFilter;
import com.loupan.boot.domain.centaec.user.UserLoginLog;
import com.loupan.boot.repository.centaec.user.UserLoginLogRepository;


@Service
public class UserLoginLogService {
	
	@Autowired
	UserLoginLogRepository userLoginLogRepository;
	
	//登录方式
	public static enum SignonType{
		pc(1),app(2);
		int num;
		private SignonType(int num) {
			this.num = num;
		}
		public int getNum() {
			return num;
		}
	}
	
	//page
	public Page<UserLoginLog> page(Map<String, Object> searchParams,Pageable pageable){
		Set<SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<UserLoginLog> spec = DynamicSpecifications.bySearchFilter(filters, UserLoginLog.class);
		return userLoginLogRepository.findAll(spec, pageable);
	}
	
	//create
	public void create(UserLoginLog userLoginLog){
		Date d = new Date();
		Long now = d.getTime()/1000;
		userLoginLog.setTimestamp(d);
		userLoginLogRepository.save(userLoginLog);
		userLoginLogRepository.updateLastSignonTime(now.intValue(), userLoginLog.getUserId());
	}
	
	//modify
	public void modify(UserLoginLog entity){
		UserLoginLog orginalEntity = userLoginLogRepository.findOne(entity.getId());
		if(orginalEntity!=null){
			BeanMapper.copyProprty(orginalEntity, entity);
			userLoginLogRepository.save(orginalEntity);
		}
	}
	//delete
	public void delete(int id){
		userLoginLogRepository.delete(id);
	}
	
}
