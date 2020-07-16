package com.qa.hobby.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.Test;
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
import com.qa.hobby.persistence.domain.Comic;
import com.qa.hobby.persistence.repo.ComicRepo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ComicControllerIntegrationTest {

	@Autowired
	private MockMvc mockMVC;

	private Comic comic;

	private Comic savedComic;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private ComicRepo repo;

	private Long id;

	@Before
	public void init() {
		this.repo.deleteAll();

		this.comic = new Comic();

		this.savedComic = this.repo.save(this.comic);

		this.id = this.savedComic.getId() + 1;
	}

	@Test
	public void testCreate() throws Exception {

		this.savedComic.setId(id);

		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/comic/create");
		reqBuilder.contentType(MediaType.APPLICATION_JSON);
		reqBuilder.accept(MediaType.APPLICATION_JSON);
		reqBuilder.content(this.mapper.writeValueAsString(comic));

		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(savedComic));

		this.mockMVC.perform(reqBuilder).andExpect(matchStatus).andExpect(matchContent);
	}

	@Test
	public void testCreateBuilder() throws Exception {
		this.mockMVC
				.perform(post("/comic/create").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(comic)))
				.andExpect(status().isCreated()).andExpect(content().json(this.mapper.writeValueAsString(savedComic)));

	}

	@Test
	public void testReadOneSuccess() throws Exception {
		this.mockMVC
				.perform(get("/comic/read/" + this.savedComic.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(this.mapper.writeValueAsString(savedComic)));
	}

	@Test
	public void testReadOneFailure() throws Exception {
		this.mockMVC.perform(
				get("/comic/read/999999").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

}
