package com.qa.hobby.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.hobby.dto.UniverseDTO;
import com.qa.hobby.persistence.domain.Comic;
import com.qa.hobby.persistence.domain.Universe;
import com.qa.hobby.service.UniverseService;


@RestController
@RequestMapping("/universe")
public class UniverseController {

	private UniverseService service;

	@Autowired
	public UniverseController(UniverseService service) {
		super();
		this.service = service;
	}

	@PostMapping("/createUniverse")
	public ResponseEntity<UniverseDTO> createUniverse(@RequestBody Universe universe) {
		return new ResponseEntity<UniverseDTO>(this.service.createUniverse(universe), HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteUniverse/{id}")
	public ResponseEntity<?> deleteUniverse(@PathVariable Long id) {
		return this.service.deleteUniverse(id) ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
				: new ResponseEntity<String>("DELETED", HttpStatus.NO_CONTENT);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<UniverseDTO> getUniverse(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.findUniverseByID(id));
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<UniverseDTO>> getAllUniverses() {
		return ResponseEntity.ok(this.service.readUniverses());
	}

	@PutMapping("/updateUniverse")
	public ResponseEntity<UniverseDTO> updateUniverse(@PathParam("id") Long id, @RequestBody Universe universe) {
		return new ResponseEntity<UniverseDTO>(this.service.updateUniverse(universe, id), HttpStatus.ACCEPTED);
	}

	@PatchMapping("/update/{id}")
	public ResponseEntity<UniverseDTO> addComicToUniverse(@PathVariable Long id, @RequestBody Comic comic) {
		return new ResponseEntity<UniverseDTO>(this.service.addComicToUniverse(id, comic), HttpStatus.ACCEPTED);
	}

}
