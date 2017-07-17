package com.loupan.boot.domain.union;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the user_union.ld_agency database table.
 * 
 */
@Entity
@Table(name="ld_agency")
@NamedQuery(name="LdAgency.findAll", query="SELECT l FROM LdAgency l")
public class LdAgency implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String address;

	@Column(name="AREA_ID")
	private Integer areaId;

	@Column(name="BELONG_TO")
	private Integer belongTo;

	@Column(name="CITY_ID")
	private Integer cityId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_TIME")
	private Date createTime;

	private String intro;

	@Column(name="IS_SIGNING")
	private Integer isSigning;

	private Double lat;

	private Integer level;

	private Double lng;

	private String name;

	@Column(name="NAME_ABBR")
	private String nameAbbr;

	@Column(name="PARENT_AGENCY_ID")
	private Integer parentAgencyId;
	
	@Column(name="PIC_INFO")
	private String picInfo;

	@Column(name="PROVINCE_ID")
	private Integer provinceId;

	@Column(name="SHOP_LEADER")
	private String shopLeader;

	@Column(name="SHOP_LEADER_PHONE")
	private String shopLeaderPhone;

	@Column(name="SHOP_MANAGER")
	private String shopManager;

	@Column(name="SHOP_MANAGER_PHONE")
	private String shopManagerPhone;

	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	private Integer type;

	public LdAgency() {
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getBelongTo() {
		return belongTo;
	}

	public void setBelongTo(Integer belongTo) {
		this.belongTo = belongTo;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Integer getIsSigning() {
		return isSigning;
	}

	public void setIsSigning(Integer isSigning) {
		this.isSigning = isSigning;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameAbbr() {
		return nameAbbr;
	}

	public void setNameAbbr(String nameAbbr) {
		this.nameAbbr = nameAbbr;
	}


	public Integer getParentAgencyId() {
		return parentAgencyId;
	}


	public void setParentAgencyId(Integer parentAgencyId) {
		this.parentAgencyId = parentAgencyId;
	}


	public String getPicInfo() {
		return picInfo;
	}

	public void setPicInfo(String picInfo) {
		this.picInfo = picInfo;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public String getShopLeader() {
		return shopLeader;
	}

	public void setShopLeader(String shopLeader) {
		this.shopLeader = shopLeader;
	}

	public String getShopLeaderPhone() {
		return shopLeaderPhone;
	}

	public void setShopLeaderPhone(String shopLeaderPhone) {
		this.shopLeaderPhone = shopLeaderPhone;
	}

	public String getShopManager() {
		return shopManager;
	}

	public void setShopManager(String shopManager) {
		this.shopManager = shopManager;
	}

	public String getShopManagerPhone() {
		return shopManagerPhone;
	}

	public void setShopManagerPhone(String shopManagerPhone) {
		this.shopManagerPhone = shopManagerPhone;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}