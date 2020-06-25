package com.qa.hobby.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.hobby.exceptions.UniverseNotFoundException;
import com.qa.hobby.persistence.domain.Universe;
import com.qa.hobby.persistence.repo.UniverseRepo;

@Service
public class UniverseService {

	private UniverseRepo repo;

	public UniverseService(UniverseRepo repo) {
		super();
		this.repo = repo;
	}

	public Universe create(Universe universe) {
		return this.repo.save(universe);

	}

	public List<Universe> read() {
		return this.repo.findAll();
	}

	public Universe read(long id) {
		return this.repo.findById(id).orElseThrow(() -> new UniverseNotFoundException());
	}

	public Universe update(Universe universe, long id) {

		Optional<Universe> optUniverse = this.repo.findById(id);

		Universe toUpdate = optUniverse.orElseThrow(() -> new UniverseNotFoundException());

		toUpdate.setName(universe.getName());

		return this.repo.save(toUpdate);
	}

	public boolean delete(long id) {
		this.repo.deleteById(id);
		return this.repo.existsById(id);
	}
}
