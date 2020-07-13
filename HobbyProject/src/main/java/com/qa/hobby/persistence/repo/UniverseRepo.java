package com.qa.hobby.persistence.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.hobby.persistence.domain.Universe;

@Repository
public interface UniverseRepo extends JpaRepository<Universe, Long> {

}
