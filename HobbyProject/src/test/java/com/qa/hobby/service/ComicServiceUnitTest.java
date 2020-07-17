package com.qa.hobby.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.hobby.dto.ComicDTO;
import com.qa.hobby.persistence.domain.Comic;
import com.qa.hobby.rest.ComicController;

@RunWith(MockitoJUnitRunner.class)
public class ComicServiceUnitTest {

	@InjectMocks
	private ComicController controller;

	@Mock
	private ComicService service;

	private List<Comic> comicList;

	private Comic testComic;

	private Comic testComicWithID;

	private ComicDTO comicDTO;

	final long id = 1L;

	private ModelMapper mapper = new ModelMapper();

	private ComicDTO mapToDTO(Comic comic) {
		return this.mapper.map(comic, ComicDTO.class);
	}

	@Before
	public void init() {
		this.comicList = new ArrayList<>();
		this.testComic = new Comic("Avengers", "Marvel", "Sam Grey", 3);
		this.comicList.add(testComic);
		this.testComicWithID = new Comic(testComic.getTitle(), testComic.getPublisher(), testComic.getWriter(),
				testComic.getIssue());
		this.testComicWithID.setId(id);
		this.comicDTO = this.mapToDTO(testComicWithID);
	}

	@Test
	public void createComicTest() {
		when(this.service.createComic(testComic)).thenReturn(this.comicDTO);

		assertEquals(new ResponseEntity<ComicDTO>(this.comicDTO, HttpStatus.CREATED),
				this.controller.createComic(testComic));

		verify(this.service, times(1)).createComic(this.testComic);
	}

	@Test
	public void deleteComicTest() {
		this.controller.deleteComic(id);

		verify(this.service, times(1)).deleteComic(id);
	}

	@Test
	public void findComicByIDTest() {
		when(this.service.findComicByID(this.id)).thenReturn(this.comicDTO);

		assertEquals(new ResponseEntity<ComicDTO>(this.comicDTO, HttpStatus.OK), this.controller.getComic(this.id));

		verify(this.service, times(1)).findComicByID(this.id);
	}

	@Test
	public void readAllComicsTest() {

		when(service.readComics()).thenReturn(this.comicList.stream().map(this::mapToDTO).collect(Collectors.toList()));

		assertFalse("Controller has found no ducks", this.controller.getAllComics().getBody().isEmpty());

		verify(service, times(1)).readComics();
	}

	@Test
	public void updateComicTest() {
		// given
		Comic newComic = new Comic("Prof.Green", "Harbro", "Melissa White", 3);
		Comic updatedComic = new Comic(newComic.getTitle(), newComic.getPublisher(), newComic.getWriter(),
				newComic.getIssue());
		updatedComic.setId(this.id);

		when(this.service.updateComic(newComic, this.id)).thenReturn(this.mapToDTO(updatedComic));

		assertEquals(new ResponseEntity<ComicDTO>(this.mapToDTO(updatedComic), HttpStatus.ACCEPTED),
				this.controller.updateComic(this.id, newComic));

		verify(this.service, times(1)).updateComic(newComic, this.id);
	}

}
