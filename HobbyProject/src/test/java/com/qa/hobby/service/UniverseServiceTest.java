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

import com.qa.hobby.dto.UniverseDTO;
import com.qa.hobby.persistence.domain.Universe;
import com.qa.hobby.rest.UniverseController;


@RunWith(MockitoJUnitRunner.class)
public class UniverseServiceTest {
	@InjectMocks
	private UniverseController controller;

	@Mock
	private UniverseService service;

	private List<Universe> universeList;

	private Universe testUniverse;

	private Universe testUniverseWithID;

	private UniverseDTO universeDTO;

	final long id = 1L;

	private ModelMapper mapper = new ModelMapper();

	private UniverseDTO mapToDTO(Universe universe) {
		return this.mapper.map(universe, UniverseDTO.class);
	}

	@Before
	public void init() {
		this.universeList = new ArrayList<>();
		this.testUniverse = new Universe("Marvel");
		this.universeList.add(testUniverse);
		this.testUniverseWithID = new Universe(testUniverse.getName());
		this.testUniverseWithID.setId(id);
		this.universeDTO = this.mapToDTO(testUniverseWithID);
	}

	@Test
	public void createUniverseTest() {
		when(this.service.createUniverse(testUniverse)).thenReturn(this.universeDTO);

		assertEquals(new ResponseEntity<UniverseDTO>(this.universeDTO, HttpStatus.CREATED),
				this.controller.createUniverse(testUniverse));

		verify(this.service, times(1)).createUniverse(this.testUniverse);
	}

	@Test
	public void deleteUniverseTest() {
		this.controller.deleteUniverse(id);

		verify(this.service, times(1)).deleteUniverse(id);
	}

	@Test
	public void findUniverseByIDTest() {
		when(this.service.findUniverseByID(this.id)).thenReturn(this.universeDTO);

		assertEquals(new ResponseEntity<UniverseDTO>(this.universeDTO, HttpStatus.OK), this.controller.getUniverse(this.id));

		verify(this.service, times(1)).findUniverseByID(this.id);
	}

	@Test
	public void readAllUniversesTest() {

		when(service.readUniverses()).thenReturn(this.universeList.stream().map(this::mapToDTO).collect(Collectors.toList()));

		assertFalse("Controller has found no ducks", this.controller.getAllUniverses().getBody().isEmpty());

		verify(service, times(1)).readUniverses();
	}

	@Test
	public void updateUniverseTest() {
		// given
		Universe newUniverse = new Universe("Hasbro");
		Universe updatedUniverse = new Universe(newUniverse.getName());
		updatedUniverse.setId(this.id);

		when(this.service.updateUniverse(newUniverse, this.id)).thenReturn(this.mapToDTO(updatedUniverse));

		assertEquals(new ResponseEntity<UniverseDTO>(this.mapToDTO(updatedUniverse), HttpStatus.ACCEPTED),
				this.controller.updateUniverse(this.id, newUniverse));

		verify(this.service, times(1)).updateUniverse(newUniverse, this.id);
	}

}