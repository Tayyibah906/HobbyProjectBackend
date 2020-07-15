package com.qa.hobby.dto;

import java.util.List;

public class UniverseDTO {
	
	private Long id;
	
	private String name;
	
	private List<ComicDTO> comic;
	
	public UniverseDTO(long id, String name, List<ComicDTO> comic) {
		super();
		this.id=id;
		this.name=name;
		this.comic=comic;
	}
	
	public UniverseDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ComicDTO> getComic() {
		return comic;
	}

	public void setComic(List<ComicDTO> comic) {
		this.comic = comic;
	}


}
