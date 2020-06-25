package com.qa.hobby.persistence.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.hobby.persistence.domain.Universe;

public interface UniverseRepo extends JpaRepository<Universe, Long> {

	List<Universe> findByName(String Name);
}
