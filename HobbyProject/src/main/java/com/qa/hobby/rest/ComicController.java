package com.qa.hobby.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.hobby.dto.ComicDTO;
import com.qa.hobby.persistence.domain.Comic;
import com.qa.hobby.service.ComicService;

@RestController
@CrossOrigin
@RequestMapping("/comic")
public class ComicController {

	private ComicService service;
	
	@Autowired
	public ComicController(ComicService service) {
		super();
		this.service = service;
	}
	

	@PostMapping("/createComic")
	public ResponseEntity<ComicDTO> create(@RequestBody Comic comic) {
		return new ResponseEntity<ComicDTO>(this.service.create(comic), HttpStatus.CREATED);
	}
	
	@GetMapping("/readComic/{id}")
	public ResponseEntity<ComicDTO> readOne(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.read(id));
	}

	@GetMapping("/readComic")
	public ResponseEntity<List<ComicDTO>> read() {
		return new ResponseEntity<List<ComicDTO>>(this.service.read(), HttpStatus.OK);
	}

	@PutMapping("/updateComic/{id}")
	public ResponseEntity<ComicDTO> update(@PathVariable Long id, @RequestBody Comic comic) {
		return new ResponseEntity<ComicDTO>(this.service.update(comic, id), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteComic/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return (this.service.delete(id) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
				: new ResponseEntity<>(HttpStatus.NO_CONTENT));
	}
	
}
