package com.loupan.boot.domain.centaec.agency;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the ld_agency_tag database table.
 * 
 */
@Entity
@Table(name="ld_agency_tag")
@NamedQuery(name="LdAgencyTag.findAll", query="SELECT l FROM LdAgencyTag l")
public class LdAgencyTag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="tag_name")
	private String tagName;

	public LdAgencyTag() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTagName() {
		return this.tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

}