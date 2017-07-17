package com.loupan.boot.domain.centaec.map;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the view_area_map database table.
 * 
 */
@Entity
@Table(name="view_area_map")
@NamedQuery(name="ViewAreaMap.findAll", query="SELECT v FROM ViewAreaMap v")
public class ViewAreaMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="area_id")
	private int areaId;

	@Column(name="area_name")
	private String areaName;

	@Column(name="city_id")
	private int cityId;

	@Column(name="city_name")
	private String cityName;

	@Column(name="province_id")
	private int provinceId;

	@Column(name="province_name")
	private String provinceName;

	public ViewAreaMap() {
	}

	public int getAreaId() {
		return this.areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public int getCityId() {
		return this.cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return this.provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

}