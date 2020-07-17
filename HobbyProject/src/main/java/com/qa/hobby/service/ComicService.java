package com.qa.hobby.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.qa.hobby.dto.ComicDTO;
import com.qa.hobby.exceptions.ComicNotFoundException;
import com.qa.hobby.persistence.domain.Comic;
import com.qa.hobby.persistence.repo.ComicRepo;
import com.qa.hobby.utils.MyBeanUtils;

@Service
public class ComicService  {

	private ComicRepo repo;

	private Mapper<Comic, ComicDTO> mapper;
	
	@Autowired
	public ComicService(ComicRepo repo, ModelMapper mapper) {
		this.repo = repo;
		this.mapper = (Comic comic) -> mapper.map(comic, ComicDTO.class);
	}

	public ComicDTO createComic(Comic comic) {
		return this.mapper.mapToDTO(this.repo.save(comic));
	}

	public boolean deleteComic(Long id) {
		if (!this.repo.existsById(id)) {
			throw new ComicNotFoundException();
		}
		this.repo.deleteById(id);
		return this.repo.existsById(id);
	}

	public ComicDTO findComicByID(Long id) {
		return this.mapper.mapToDTO(this.repo.findById(id).orElseThrow(ComicNotFoundException::new));
	}

	public List<ComicDTO> readComics() {
		return this.repo.findAll().stream().map(this.mapper::mapToDTO).collect(Collectors.toList());
	}

	public ComicDTO updateComic(Comic comic, Long id) {
		Comic toUpdate = this.repo.findById(id).orElseThrow(ComicNotFoundException::new);
		MyBeanUtils.mergeNotNull(comic, toUpdate);
		return this.mapper.mapToDTO(this.repo.save(toUpdate));
	}

}
