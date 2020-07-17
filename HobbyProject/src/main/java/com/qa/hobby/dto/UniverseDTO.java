package com.qa.hobby.dto;

import java.util.ArrayList;
import java.util.List;


public class UniverseDTO {

	private Long id;

	private String name;

	private List<ComicDTO> comics = new ArrayList<>();

	public UniverseDTO(String name) {
		this.name = name;
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

	public List<ComicDTO> getcomics() {
		return comics;
	}

	public void setcomics(List<ComicDTO> comics) {
		this.comics = comics;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comics == null) ? 0 : comics.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UniverseDTO other = (UniverseDTO) obj;
		if (comics == null) {
			if (other.comics != null)
				return false;
		} else if (!comics.equals(other.comics))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
}
