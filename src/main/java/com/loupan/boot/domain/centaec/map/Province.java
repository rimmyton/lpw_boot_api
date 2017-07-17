package com.loupan.boot.domain.centaec.map;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the province database table.
 * 
 */
@Entity
@NamedQuery(name="Province.findAll", query="SELECT p FROM Province p order by p.id")
public class Province implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;
	
	@OneToMany(mappedBy="province")
	private List<City> citys;
	
	public Province() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}