package com.qa.hobby.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.hobby.exceptions.ComicNotFoundException;
import com.qa.hobby.persistence.domain.Comic;
import com.qa.hobby.persistence.repo.ComicRepo;

@Service
public class ComicService {
	
	private ComicRepo repo;
	
	public ComicService(ComicRepo repo) {
		super();
		this.repo = repo;
	}
	
//	INSERT INTO comic VALUES...
	public Comic create(Comic comic) {
		return this.repo.save(comic);
	}

//	SELECT * FROM comic
	public List<Comic> read(){
		return this.repo.findAll();
	}

//	SELECT FROM comic WHERE id =
	public Comic read(long id) {
		return this.repo.findById(id).orElseThrow(() -> new ComicNotFoundException());
	}
	
//	UPDATE comic SET ....
	public Comic update(Comic comic, long id) {
		Optional<Comic> optComic = this.repo.findById(id);
		
		Comic toUpdate = optComic.orElseThrow(() -> new ComicNotFoundException());
		
		toUpdate.setTitle(comic.getTitle());
		toUpdate.setPublisher(comic.getPublisher());
		toUpdate.setWriter(comic.getWriter());
		toUpdate.setCoverArtist(comic.getCoverArtist());
		toUpdate.setIssue(comic.getIssue());
		
		return this.repo.save(toUpdate);
	}
	
	
//	DELETE FROM comic WHERE id = 
	public boolean delete(long id) {
		this.repo.deleteById(id);
		return this.repo.existsById(id);
	}
}
