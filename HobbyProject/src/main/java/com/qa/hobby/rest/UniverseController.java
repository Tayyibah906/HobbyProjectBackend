package com.qa.hobby.rest;

import java.util.List;

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

import com.qa.hobby.dto.UniverseDTO;
import com.qa.hobby.persistence.domain.Universe;
import com.qa.hobby.service.UniverseService;

@RestController
@CrossOrigin
@RequestMapping("/universe")
public class UniverseController {
	

	private UniverseService service;

	public UniverseController(UniverseService service) {
		super();
		this.service = service;
	}

	@PostMapping("/createUniverse")
	public ResponseEntity<UniverseDTO> create(@RequestBody Universe comic) {
		return new ResponseEntity<UniverseDTO>(this.service.create(comic), HttpStatus.CREATED);
	}
	
	@GetMapping("/readUniverse/{id}")
	public ResponseEntity<UniverseDTO> readOne(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.read(id));
	}

	@GetMapping("/readUniverse")
	public ResponseEntity<List<UniverseDTO>> read() {
		return new ResponseEntity<List<UniverseDTO>>(this.service.read(), HttpStatus.OK);
	}

	@PutMapping("/updateUniverse/{id}")
	public ResponseEntity<UniverseDTO> update(@PathVariable Long id, @RequestBody Universe comic) {
		return new ResponseEntity<UniverseDTO>(this.service.update(comic, id), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteUniverse/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return (this.service.delete(id) ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) : new ResponseEntity<>(HttpStatus.NO_CONTENT));
	}

}
