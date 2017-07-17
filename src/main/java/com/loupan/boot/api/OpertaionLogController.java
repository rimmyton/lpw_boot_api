package com.loupan.boot.api;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.loupan.boot.base.BeanMapper;
import com.loupan.boot.base.MediaTypes;
import com.loupan.boot.base.Servlets;
import com.loupan.boot.domain.centaec.estate.LdEstate;
import com.loupan.boot.domain.centaec.log.LdTbLog;
import com.loupan.boot.domain.centaec.log.LdTbLogContent;
import com.loupan.boot.dto.Feedback;
import com.loupan.boot.dto.LdTbLogContentDto;
import com.loupan.boot.dto.LdTbLogDto;
import com.loupan.boot.service.estate.LdEstateService;
import com.loupan.boot.service.log.LdLogContentService;
import com.loupan.boot.service.log.LdLogService;

@RestController
public class OpertaionLogController {

	@Autowired
	private LdLogService ldLogService;

	@Autowired
	private LdLogContentService ldLogContentService;

	@Autowired
	private LdEstateService ldEastateService;

	private LdTbLogContent logContent;
	private LdTbLog log;

	@RequestMapping(value = "/api/oper/saveOperation", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	public Object saveOperation(String data) throws Exception {
		JSONObject dataJson = new JSONObject().parseObject(data);
		LdEstate estateNew = dataJson.getObject("newEstate", LdEstate.class);
		log = dataJson.getObject("ldTblog", LdTbLog.class);
		LdEstate estateOld = dataJson.getObject("oldEstate", LdEstate.class);
		String tableName = dataJson.getString("tableName");
		List<Map<String, Object>> commonList = ldEastateService.findEstateComment(tableName);
		Map<String, Object> tableCommon = ldEastateService.findEstateTablteComment(tableName);
		try {
			if(log!=null){
				List<LdTbLogContent> itContent = ldLogService.contrastObj(estateOld, estateNew, commonList);
				log.setComment(tableCommon.get("Comment").toString());
				log.setTablename(tableName);
				for(LdTbLogContent logContent : itContent){
					log.addLogContent(logContent);
				}
				log = ldLogService.saveOperation(log);
				}
		} catch (ServiceException e) {
			return new Feedback(false, e.getMessage());
		}
		return new Feedback();
	}

	@RequestMapping(value = "/api/oper/operList/page", produces = MediaTypes.JSON_UTF_8)
	public Page<LdTbLogDto> ldTbLogList(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "15") Integer size,
			@RequestParam(value = "projectid") String projectid, HttpServletRequest request) {
		Sort sort = new Sort(Direction.DESC, "tbid");
		Pageable pageable = new PageRequest(page - 1, size, sort);
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, Servlets.SEARCH_PRE);
		Page<LdTbLog> logInfo = ldLogService.findLdTbLogAll(searchParams, pageable, projectid);
	    Page<LdTbLogDto> pageInfo = new PageImpl<>(BeanMapper.mapList(logInfo, LdTbLog.class,LdTbLogDto.class), pageable, logInfo.getTotalElements());
		return pageInfo;
	}

	@RequestMapping(value = "/api/oper/operListContent/page", produces = MediaTypes.JSON_UTF_8)
	public Page<LdTbLogContentDto> ldTbLogContentList(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "15") Integer size, @RequestParam(value = "pid") String pid,
			HttpServletRequest request) {
		Sort sort = new Sort(Direction.DESC, "tbid");
		Pageable pageable = new PageRequest(page - 1, size, sort);
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, Servlets.SEARCH_PRE);
		Page<LdTbLogContent> logContentInfo = ldLogContentService.findLdTbLogContentAll(searchParams, pageable, pid);
	    Page<LdTbLogContentDto> pageInfo = new PageImpl<>(BeanMapper.mapList(logContentInfo, LdTbLogContent.class,LdTbLogContentDto.class), pageable, logContentInfo.getTotalElements());
		return pageInfo;
	}

	public LdTbLogContent getLogContent() {
		return logContent;
	}

	public void setLogContent(LdTbLogContent logContent) {
		this.logContent = logContent;
	}

	public LdTbLog getLog() {
		return log;
	}

	public void setLog(LdTbLog log) {
		this.log = log;
	}

}
