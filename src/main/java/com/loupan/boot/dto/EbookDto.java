package com.loupan.boot.dto;

import java.io.Serializable;
import java.util.Date;


public class EbookDto implements Serializable {
	private Integer id;

	private String ebookAuthor;

	private String ebookName;

	private String ebookUrl;

	private Date timestamp;

	public EbookDto() {
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getEbookAuthor() {
		return this.ebookAuthor;
	}

	public void setEbookAuthor(String ebookAuthor) {
		this.ebookAuthor = ebookAuthor;
	}

	public String getEbookName() {
		return this.ebookName;
	}

	public void setEbookName(String ebookName) {
		this.ebookName = ebookName;
	}

	public String getEbookUrl() {
		return this.ebookUrl;
	}

	public void setEbookUrl(String ebookUrl) {
		this.ebookUrl = ebookUrl;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}