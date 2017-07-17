package com.loupan.boot.domain.centaec.log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * The persistent class for the ld_tb_log database table.
 * 
 */
@Entity
@Table(name="ld_tb_log")
@NamedQuery(name="LdTbLog.findAll", query="SELECT l FROM LdTbLog l")
public class LdTbLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="tbid")
	private Integer tbid;
	
	@Column(name="adminid")
	private int adminid;
	
	@Column(name="comment")
	private String comment;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt")
	private Date dt;
	
	@Column(name="projectid")
	private int projectId;
	
	@Column(name="tablename")
	private String tablename;
	
	@Column(name="type")
	private int type;

	@Column(name="adminname")
	private String adminName;
	public LdTbLog() {
	}

    @OneToMany(cascade =CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ldTbLog")   
	private List<LdTbLogContent> items = new ArrayList<LdTbLogContent>();

	
	public Integer getTbid() {
		return this.tbid;
	}

	public void setTbid(Integer tbid) {
		this.tbid = tbid;
	}

	public int getAdminid() {
		return this.adminid;
	}

	public void setAdminid(int adminid) {
		this.adminid = adminid;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDt() {
		return this.dt;
	}

	public void setDt(Date dt) {
		
		this.dt = dt;
	}

	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getTablename() {
		return this.tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public List<LdTbLogContent> getItems() {
		return items;
	}

	public void setItems(List<LdTbLogContent> items) {
		this.items = items;
	}

	public void addLogContent(LdTbLogContent ldTbLogContent) {
		ldTbLogContent.setLdTbLog(this);
	        this.items.add(ldTbLogContent);
	 } 
}