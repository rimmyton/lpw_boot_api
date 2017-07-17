package com.loupan.boot.dto;

import java.util.ArrayList;
import java.util.List;


public class EbookCategoryDto {
	private Integer id;
	private String name;
	List<EbookDto> ebookDtos =new ArrayList<>();

	public EbookCategoryDto() {
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

	public List<EbookDto> getEbookDtos() {
		return ebookDtos;
	}

	public void setEbookDtos(List<EbookDto> ebookDtos) {
		this.ebookDtos = ebookDtos;
	}

}