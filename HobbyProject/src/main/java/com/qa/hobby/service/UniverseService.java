package com.qa.hobby.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.hobby.dto.UniverseDTO;
import com.qa.hobby.exceptions.UniverseNotFoundException;
import com.qa.hobby.persistence.domain.Comic;
import com.qa.hobby.persistence.domain.Universe;
import com.qa.hobby.persistence.repo.ComicRepo;
import com.qa.hobby.persistence.repo.UniverseRepo;

@Service
public class UniverseService {

	private UniverseRepo repo;

	private ComicRepo comicRepo;

	private Mapper<Universe, UniverseDTO> mapper;

	@Autowired
	public UniverseService(UniverseRepo repo, ComicRepo comicRepo, ModelMapper mapper) {
		this.repo = repo;
		this.comicRepo = comicRepo;
		this.mapper = (Universe universe) -> mapper.map(universe, UniverseDTO.class);
	}


	public UniverseDTO createUniverse(Universe universe) {
		return this.mapper.mapToDTO(this.repo.save(universe));
	}

	public boolean deleteUniverse(Long id) {
		if (!this.repo.existsById(id)) {
			throw new UniverseNotFoundException();
		}
		this.repo.deleteById(id);
		return this.repo.existsById(id);
	}

	public UniverseDTO findUniverseByID(Long id) {
		return this.mapper.mapToDTO(this.repo.findById(id).orElseThrow(() -> new UniverseNotFoundException()));
	}

	public List<UniverseDTO> readUniverses() {
		return this.repo.findAll().stream().map(this.mapper::mapToDTO).collect(Collectors.toList());
	}

	public UniverseDTO updateUniverse(Universe comic, Long id) {
		Universe toUpdate = this.repo.findById(id).orElseThrow(() -> new UniverseNotFoundException());
		toUpdate.setName(comic.getName());
		return this.mapper.mapToDTO(this.repo.save(toUpdate));
	}

	public UniverseDTO addComicToUniverse(Long id, Comic comic) {
		Universe toUpdate = this.repo.findById(id).orElseThrow(() -> new UniverseNotFoundException());
		Comic newComic = this.comicRepo.save(comic);
		toUpdate.getcomics().add(newComic);
		return this.mapper.mapToDTO(this.repo.saveAndFlush(toUpdate));
	}

}
