package com.loupan.boot.dto;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AgencyLineDto {

	public Long id;//公司ID
	public String name;//公司名称
	public long departmentCount;//统计公司门店数量
	public long agentCount;//统计公司下经纪人数量
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date timestamp;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	
	
	
}