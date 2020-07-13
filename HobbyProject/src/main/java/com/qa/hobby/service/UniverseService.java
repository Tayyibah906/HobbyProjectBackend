package com.qa.hobby.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import com.qa.hobby.dto.UniverseDTO;
import com.qa.hobby.exceptions.ComicNotFoundException;
import com.qa.hobby.persistence.domain.Universe;

import com.qa.hobby.persistence.repo.UniverseRepo;

@Service
public class UniverseService {

	private UniverseRepo repo;
	private ModelMapper mapper;
	
	public UniverseService(UniverseRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private UniverseDTO mapToDTO(Universe universe) {
		return this.mapper.map(universe, UniverseDTO.class);
	}
	
//	INSERT INTO universe VALUES...
	public UniverseDTO create(Universe universe) {
		Universe saved= this.repo.save(universe);
		return this.mapToDTO(saved);
	}

//	SELECT * FROM universe
	public List<UniverseDTO> read() {
		List<UniverseDTO> DTOs = new ArrayList<>();
		for (Universe universe : this.repo.findAll()) {
			DTOs.add(this.mapToDTO(universe));
		}
		return DTOs;
	}
	
//	SELECT FROM universe WHERE id =
	public UniverseDTO read(long id) {
		Universe found = this.repo.findById(id).orElseThrow(() -> new ComicNotFoundException());
		return this.mapToDTO(found);
	}
	
//	UPDATE universe SET ....
	public UniverseDTO update(Universe universe, long id) {
		
		Optional<Universe> optUniverse = this.repo.findById(id);
		
		Universe toUpdate = optUniverse.orElseThrow(() -> new ComicNotFoundException());
		
		toUpdate.setName(universe.getName());
		
		Universe updated= this.repo.save(toUpdate);
		return this.mapToDTO(updated);
	}
	
	
//	DELETE FROM universe WHERE id = 
	public boolean delete(long id) {
		this.repo.deleteById(id);
		return this.repo.existsById(id);
	}
}

