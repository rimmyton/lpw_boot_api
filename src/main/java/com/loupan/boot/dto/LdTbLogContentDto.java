package com.loupan.boot.dto;

public class LdTbLogContentDto {

	private int tbid;
	private String newdata;
	private String olddata;
	private int p_id;
	private String tbkey;
	private String tbvalue;
	public int getTbid() {
		return tbid;
	}
	public void setTbid(int tbid) {
		this.tbid = tbid;
	}
	public String getNewdata() {
		return newdata;
	}
	public void setNewdata(String newdata) {
		this.newdata = newdata;
	}
	public String getOlddata() {
		return olddata;
	}
	public void setOlddata(String olddata) {
		this.olddata = olddata;
	}
	public String getTbkey() {
		return tbkey;
	}
	public void setTbkey(String tbkey) {
		this.tbkey = tbkey;
	}
	public String getTbvalue() {
		return tbvalue;
	}
	public void setTbvalue(String tbvalue) {
		this.tbvalue = tbvalue;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

}
