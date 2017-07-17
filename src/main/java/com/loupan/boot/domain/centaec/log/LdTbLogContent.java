package com.loupan.boot.domain.centaec.log;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the ld_tb_log_content database table.
 * 
 */
@Entity
@Table(name="ld_tb_log_content")
@NamedQuery(name="LdTbLogContent.findAll", query="SELECT l FROM LdTbLogContent l")
public class LdTbLogContent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tbid;
	
	@Column(name="newdata")
	private String newdata;
	
	@Column(name="olddata")
	private String olddata;
	
	
	@Column(name="tbkey")
	private String tbkey;
	
	@Column(name="tbvalue")
	private String tbvalue;

	@ManyToOne(cascade ={CascadeType.REFRESH, CascadeType.MERGE }, optional = false)
	@JoinColumn(name="p_id")
	private LdTbLog ldTbLog;
	
	public LdTbLogContent() {
	}

	public int getTbid() {
		return this.tbid;
	}

	public void setTbid(int tbid) {
		this.tbid = tbid;
	}

	public String getNewdata() {
		return this.newdata;
	}

	public void setNewdata(String newdata) {
		this.newdata = newdata;
	}

	public String getOlddata() {
		return this.olddata;
	}

	public void setOlddata(String olddata) {
		this.olddata = olddata;
	}


	public String getTbkey() {
		return this.tbkey;
	}

	public void setTbkey(String tbkey) {
		this.tbkey = tbkey;
	}

	public String getTbvalue() {
		return this.tbvalue;
	}

	public void setTbvalue(String tbvalue) {
		this.tbvalue = tbvalue;
	}

	public LdTbLog getLdTbLog() {
		return ldTbLog;
	}

	public void setLdTbLog(LdTbLog ldTbLog) {
		this.ldTbLog = ldTbLog;
	}


}