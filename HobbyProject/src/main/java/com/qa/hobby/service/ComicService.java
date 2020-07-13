package com.qa.hobby.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.hobby.dto.ComicDTO;
import com.qa.hobby.exceptions.ComicNotFoundException;
import com.qa.hobby.persistence.domain.Comic;
import com.qa.hobby.persistence.repo.ComicRepo;

@Service
public class ComicService {
	
	private ComicRepo repo;
	private ModelMapper mapper;
	
	public ComicService(ComicRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private ComicDTO mapToDTO(Comic comic) {
		return this.mapper.map(comic, ComicDTO.class);
	}
	
//	INSERT INTO comic VALUES...
	public ComicDTO create(Comic comic) {
		Comic saved= this.repo.save(comic);
		return this.mapToDTO(saved);
	}

//	SELECT * FROM comic
	public List<ComicDTO> read() {
		List<ComicDTO> DTOs = new ArrayList<>();
		for (Comic comic : this.repo.findAll()) {
			DTOs.add(this.mapToDTO(comic));
		}
		return DTOs;
	}
	
//	SELECT FROM comic WHERE id =
	public ComicDTO read(long id) {
		Comic found = this.repo.findById(id).orElseThrow(() -> new ComicNotFoundException());
		return this.mapToDTO(found);
	}
	
//	UPDATE comic SET ....
	public ComicDTO update(Comic comic, long id) {
		
		Optional<Comic> optComic = this.repo.findById(id);
		
		Comic toUpdate = optComic.orElseThrow(() -> new ComicNotFoundException());
		
		toUpdate.setTitle(comic.getTitle());
		toUpdate.setPublisher(comic.getPublisher());
		toUpdate.setWriter(comic.getWriter());
		toUpdate.setCoverArtist(comic.getCoverArtist());
		toUpdate.setIssue(comic.getIssue());
		
		Comic updated= this.repo.save(toUpdate);
		return this.mapToDTO(updated);
	}
	
	
//	DELETE FROM comic WHERE id = 
	public boolean delete(long id) {
		this.repo.deleteById(id);
		return this.repo.existsById(id);
	}
}
