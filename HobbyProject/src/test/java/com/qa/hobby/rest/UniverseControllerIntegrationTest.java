package com.qa.hobby.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.hobby.persistence.domain.Universe;
import com.qa.hobby.persistence.repo.UniverseRepo;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UniverseControllerIntegrationTest {


	@Autowired
	private MockMvc mockMVC;

	private Universe universe;

	private Universe savedUniverse;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private UniverseRepo repo;

	private Long id;

	@Before
	public void init() {
		this.repo.deleteAll();

		this.universe = new Universe();

		this.savedUniverse = this.repo.save(this.universe);

		this.id = this.savedUniverse.getId() + 1;
	}


	@Test
	public void testCreate() throws Exception {

		this.savedUniverse.setId(id);

		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/universe/create");
		reqBuilder.contentType(MediaType.APPLICATION_JSON);
		reqBuilder.accept(MediaType.APPLICATION_JSON);
		reqBuilder.content(this.mapper.writeValueAsString(universe));

		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(savedUniverse));

		this.mockMVC.perform(reqBuilder).andExpect(matchStatus).andExpect(matchContent);
	}

	@Test
	public void testCreateBuilder() throws Exception {
		this.mockMVC
				.perform(post("/universe/create").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(universe)))
				.andExpect(status().isCreated()).andExpect(content().json(this.mapper.writeValueAsString(savedUniverse)));

	}

	@Test
	public void testReadOneSuccess() throws Exception {
		this.mockMVC
				.perform(get("/universe/read/" + this.savedUniverse.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(this.mapper.writeValueAsString(savedUniverse)));
	}

	@Test
	public void testReadOneFailure() throws Exception {
		this.mockMVC.perform(
				get("/universe/read/999999").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
}
