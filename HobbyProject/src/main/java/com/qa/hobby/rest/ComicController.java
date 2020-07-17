package com.qa.hobby.rest;

import java.util.List;

import javax.websocket.server.PathParam;

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
@RequestMapping("/comic")
@CrossOrigin
public class ComicController {

	private ComicService service;

	@Autowired
	public ComicController(ComicService service) {
		super();
		this.service = service;
	}

	@PostMapping("/createComic")
	public ResponseEntity<ComicDTO> createComic(@RequestBody Comic comic) {
		return new ResponseEntity<ComicDTO>(this.service.createComic(comic), HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteComic/{id}")
	public ResponseEntity<?> deleteComic(@PathVariable Long id) {
		return this.service.deleteComic(id) ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
				: new ResponseEntity<String>("DELETED", HttpStatus.NO_CONTENT);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<ComicDTO> getComic(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.findComicByID(id));
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<ComicDTO>> getAllComics() {
		return ResponseEntity.ok(this.service.readComics());
	}

	@PutMapping("/updateComic")
	public ResponseEntity<ComicDTO> updateComic(@PathParam("id") Long id, @RequestBody Comic comic) {
		return new ResponseEntity<ComicDTO>(this.service.updateComic(comic, id), HttpStatus.ACCEPTED);
	}

}
