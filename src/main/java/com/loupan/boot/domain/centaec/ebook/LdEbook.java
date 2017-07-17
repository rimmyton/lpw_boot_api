package com.loupan.boot.domain.centaec.ebook;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the ld_ebook database table.
 * 
 */
@Entity
@Table(name="ld_ebook")
@NamedQuery(name="LdEbook.findAll", query="SELECT l FROM LdEbook l")
public class LdEbook implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="ebook_author")
	private String ebookAuthor;

	@Column(name="ebook_name")
	private String ebookName;

	@ManyToOne
	@JoinColumn(name="ebook_category_id")
	private LdEbookCategory ldEbookCategory;

	@Column(name="ebook_url")
	private String ebookUrl;

	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	public LdEbook() {
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

	public LdEbookCategory getLdEbookCategory() {
		return ldEbookCategory;
	}

	public void setLdEbookCategory(LdEbookCategory ldEbookCategory) {
		this.ldEbookCategory = ldEbookCategory;
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