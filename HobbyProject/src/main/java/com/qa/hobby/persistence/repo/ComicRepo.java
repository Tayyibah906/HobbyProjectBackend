package com.qa.hobby.persistence.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.hobby.persistence.domain.Comic;

@Repository
public interface ComicRepo extends JpaRepository<Comic, Long>{

	List<Comic> findBytitle(String Title);
}
