package com.loupan.boot.dto;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

//
public class DepartmentLineDto {

	public Long id;//门店ID
	public String name;//门店名称
	public Integer areaId;
	public String areaName ="未知";//门店城市
	public Integer provinceId;
	public String provinceName ="未知";//门店城市
	public Integer cityId;
	public String cityName ="未知";//门店城市
	public Integer agentCount=0;//统计公司下经纪人数量
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date timestamp;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}