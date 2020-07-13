package com.qa.hobby.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.hobby.persistence.domain.Comic;
import com.qa.hobby.persistence.repo.ComicRepo;


@RunWith(MockitoJUnitRunner.class)
public class ComicServiceTest {


	private final Comic COMIC = new Comic("Batman Adventures", "John smith", "Claire smith", "Moo", 100);

	private Comic savedComic;

	@Mock
	private ComicRepo repo;

	@InjectMocks
	private ComicService service;

	@Before
	public void init() {
		this.savedComic = new Comic (COMIC.getTitle(),COMIC.getWriter(), COMIC.getCoverArtist(), 
					COMIC.getPublisher(), COMIC.getIssue());
		this.savedComic.setId(1L);
	}

	@Test
	public void testCreate() {
		when(this.repo.save(COMIC)).thenReturn(savedComic);

		assertEquals(savedComic, service.create(COMIC));

		verify(this.repo, Mockito.times(1)).save(COMIC);
	}

	@Test
	public void testUpdate() {
		Mockito.when(this.repo.findById(savedComic.getId())).thenReturn(Optional.of(savedComic));

		Comic newComic = new Comic();
		Comic newComicWthID = new Comic();
		newComicWthID.setId(savedComic.getId());

		Mockito.when(this.repo.save(newComicWthID)).thenReturn(newComicWthID);

		assertEquals(newComicWthID, this.service.update(newComic, savedComic.getId()));

		Mockito.verify(this.repo, Mockito.times(1)).findById(savedComic.getId());
		Mockito.verify(this.repo, Mockito.times(1)).save(newComicWthID);
	}

	@Test
	public void testDeleteFails() {
		final long ID = 1L;
		final boolean RESULT = true;
		Mockito.when(this.repo.existsById(ID)).thenReturn(RESULT);

		assertEquals(RESULT, this.service.delete(ID));

		Mockito.verify(this.repo, Mockito.times(1)).existsById(ID);

	}

	@Test
	public void testDeleteSucceeds() {
		final long ID = 1L;
		final boolean RESULT = false;
		Mockito.when(this.repo.existsById(ID)).thenReturn(RESULT);

		assertEquals(RESULT, this.service.delete(ID));

		Mockito.verify(this.repo, Mockito.times(1)).existsById(ID);
	}

}
