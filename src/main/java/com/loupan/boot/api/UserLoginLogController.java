package com.loupan.boot.api;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loupan.boot.base.MediaTypes;
import com.loupan.boot.base.Servlets;
import com.loupan.boot.domain.centaec.user.UserLoginLog;
import com.loupan.boot.dto.Feedback;
import com.loupan.boot.service.user.UserLoginLogService;

//Spring Restful MVC Controller的标识, 直接输出内容，不调用template引擎.
@RestController
public class UserLoginLogController {
	
	@Autowired
	private UserLoginLogService userLoginLogService;
	
	@RequestMapping(value = "/api/user/loginLog/page", produces = MediaTypes.JSON_UTF_8)
	public Page<UserLoginLog> page(@RequestParam(value = "page", defaultValue = "1") Integer page,
	        @RequestParam(value = "size", defaultValue = "15") Integer size,HttpServletRequest request) {
		Sort sort = new Sort(Direction.DESC, "id");
	    Pageable pageable = new PageRequest(page-1, size, sort);
	    Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, Servlets.SEARCH_PRE);
	    
	    Page<UserLoginLog> pageInfo = userLoginLogService.page(searchParams, pageable);
	    return  pageInfo;
	}
	
	@RequestMapping(value = "/api/user/loginLog/pc", produces = MediaTypes.JSON_UTF_8)
	public Feedback pcLogin(UserLoginLog userLoginLog) {
		userLoginLog.setSignonType(UserLoginLogService.SignonType.pc.getNum());
		userLoginLogService.create(userLoginLog);
	    return  new Feedback();
	}
	
	@RequestMapping(value = "/api/user/loginLog/app", produces = MediaTypes.JSON_UTF_8)
	public Feedback appLogin(UserLoginLog userLoginLog) {
		userLoginLog.setSignonType(UserLoginLogService.SignonType.app.getNum());
		userLoginLogService.create(userLoginLog);
		return  new Feedback();
	}
}
