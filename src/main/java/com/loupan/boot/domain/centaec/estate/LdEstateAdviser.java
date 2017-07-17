package com.loupan.boot.domain.centaec.estate;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * 项目的置业顾问
 * 
 */
@Entity
@Table(name="ld_estate_adviser")
@NamedQuery(name="LdEstateAdviser.findAll", query="SELECT l FROM LdEstateAdviser l")
public class LdEstateAdviser implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="adviser_name")
	private String adviserName;

	@Column(name="adviser_phone")
	private String adviserPhone;

	@Column(name="est_id")
	private Integer estId;

	public LdEstateAdviser() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdviserName() {
		return adviserName;
	}

	public void setAdviserName(String adviserName) {
		this.adviserName = adviserName;
	}

	public String getAdviserPhone() {
		return adviserPhone;
	}

	public void setAdviserPhone(String adviserPhone) {
		this.adviserPhone = adviserPhone;
	}

	public Integer getEstId() {
		return estId;
	}

	public void setEstId(Integer estId) {
		this.estId = estId;
	}


}