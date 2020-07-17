package com.qa.hobby.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.hobby.persistence.domain.Universe;
import com.qa.hobby.persistence.repo.UniverseRepo;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UniverseControllerIntegrationTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private UniverseRepo repo;

	private ObjectMapper mapper = new ObjectMapper();

	private long id;

	private Universe testUniverse;

	private Universe testUniverseWithID;

	@Before
	public void init() {
		this.repo.deleteAll();
		this.testUniverse = new Universe("Archie");
		this.testUniverseWithID = this.repo.save(this.testUniverse);
		this.id = this.testUniverseWithID.getId();
	}

	@Test
	public void CreateUniverseTest() throws Exception {
		String result = this.mock
				.perform(request(HttpMethod.POST, "/universe/createUniverse").contentType(MediaType.APPLICATION_JSON)
						.content(this.mapper.writeValueAsString(testUniverse)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
		assertEquals(this.mapper.writeValueAsString(testUniverseWithID), result);
	}

	@Test
	public void DeleteUniverseTest() throws Exception {
		this.mock.perform(request(HttpMethod.DELETE, "/universe/deleteUniverse/" + this.id)).andExpect(status().isNoContent());
	}

	@Test
	public void FindUniverseByIdTest() throws Exception {
		String content = this.mock
				.perform(request(HttpMethod.GET, "/universe/get/" + this.id).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals(this.mapper.writeValueAsString(this.testUniverse), content);
	}

	@Test
	public void ReadAllUniversesTest() throws Exception {
		List<Universe> universeList = new ArrayList<>();
		universeList.add(this.testUniverseWithID);

		String content = this.mock.perform(request(HttpMethod.GET, "/universe/getAll").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals(this.mapper.writeValueAsString(universeList), content);
	}

	@Test
	public void UpdateUniverseTest() throws Exception {
		Universe newUniverse = new Universe("Marvel");
		Universe updatedUniverse = new Universe(newUniverse.getName());
		updatedUniverse.setId(this.id);

		String result = this.mock
				.perform(request(HttpMethod.PUT, "/universe/updateUniverse/?id=" + this.id).accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(newUniverse)))
				.andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();

		assertEquals(this.mapper.writeValueAsString(updatedUniverse), result);
	}

}
