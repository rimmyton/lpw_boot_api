package com.loupan.boot.dto;

import com.loupan.boot.domain.centaec.map.ViewAreaMap;

public class RegionDto {
	private int id;

	private int areaId;
	
	private ViewAreaMap viewAreaMap;

	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public ViewAreaMap getViewAreaMap() {
		return viewAreaMap;
	}

	public void setViewAreaMap(ViewAreaMap viewAreaMap) {
		this.viewAreaMap = viewAreaMap;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
