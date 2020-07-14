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
import org.modelmapper.ModelMapper;

import com.qa.hobby.dto.UniverseDTO;
import com.qa.hobby.persistence.domain.Universe;
import com.qa.hobby.persistence.repo.UniverseRepo;

@RunWith(MockitoJUnitRunner.class)
public class UniverseServiceTest {

	private Universe universe;

	private UniverseDTO universeDTO;
	
	
	private Universe savedUniverse;

	@Mock
	private UniverseRepo repo;
	
	@Mock
	private ModelMapper mapper;

	@InjectMocks
	private UniverseService service;

	@Before
	public void init() {
		this.universe = new Universe();
		this.universe.setName("Marvel");
		this.savedUniverse = new Universe();
		this.savedUniverse.setName(this.universe.getName());
		this.savedUniverse.setId(1L);
	}

	@Test
	public void testCreate() {
		when(this.repo.save(universe)).thenReturn(savedUniverse);
		when(this.mapper.map(savedUniverse, UniverseDTO.class)).thenReturn(universeDTO);

		assertEquals(this.universeDTO, this.service.create(savedUniverse));

		verify(this.repo, Mockito.times(1)).save(universe);
	}

	@Test
	public void testUpdate() {
		Mockito.when(this.repo.findById(savedUniverse.getId())).thenReturn(Optional.of(savedUniverse));

		Universe newUniverse = new Universe();
		newUniverse.setName("DC");
		
		Universe newUniverseWthID = new Universe();
		newUniverseWthID.setName(newUniverse.getName());
		newUniverseWthID.setId(savedUniverse.getId());

		Mockito.when(this.repo.save(newUniverseWthID)).thenReturn(newUniverseWthID);

		assertEquals(newUniverseWthID, this.service.update(newUniverse, savedUniverse.getId()));

		Mockito.verify(this.repo, Mockito.times(1)).findById(savedUniverse.getId());
		Mockito.verify(this.repo, Mockito.times(1)).save(newUniverseWthID);
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
