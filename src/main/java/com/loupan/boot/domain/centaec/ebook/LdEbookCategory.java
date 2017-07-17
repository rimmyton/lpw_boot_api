package com.loupan.boot.domain.centaec.ebook;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the ld_ebook_category database table.
 * 
 */
@Entity
@Table(name="ld_ebook_category")
@NamedQuery(name="LdEbookCategory.findAll", query="SELECT l FROM LdEbookCategory l")
public class LdEbookCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	public LdEbookCategory() {
	}

	

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}