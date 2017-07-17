package com.loupan.boot.domain.centaec.user;

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

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * The persistent class for the user_login_log database table.
 * 
 */
@Entity
@Table(name="user_login_log")
@NamedQuery(name="UserLoginLog.findAll", query="SELECT u FROM UserLoginLog u")
public class UserLoginLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="signon_ip")
	private String signonIp;//登录IP

	@Column(name="user_account")
	private String userAccount;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="user_role")
	private String userRole;

	@Column(name="user_id")
	private int userId;
	
	@Column(name="signon_type")
	private int signonType;//1：pc端，2：移动端
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@Column(nullable=false)
	private Date timestamp;

	public UserLoginLog() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSignonIp() {
		return signonIp;
	}

	public void setSignonIp(String signonIp) {
		this.signonIp = signonIp;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSignonType() {
		return signonType;
	}

	public void setSignonType(int signonType) {
		this.signonType = signonType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}


}