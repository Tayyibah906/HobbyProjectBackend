package com.qa.hobby.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.hobby.dto.ComicDTO;
import com.qa.hobby.persistence.domain.Comic;
import com.qa.hobby.persistence.repo.ComicRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ComicControllerIntegrationTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private ComicRepo repo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ObjectMapper mapper;

	private long id;

	private Comic testComic;

	private Comic testComicWithID;

	private ComicDTO comicDTO;

	private ComicDTO mapToDTO(Comic comic) {
		return this.modelMapper.map(comic, ComicDTO.class);
	}

	@Before
	public void init() {
		this.repo.deleteAll();
		this.testComic = new Comic("Batman", "blue", "John wayne", 4);
		this.testComicWithID = this.repo.save(this.testComic);
		this.id = this.testComicWithID.getId();
		this.comicDTO = this.mapToDTO(testComicWithID);
	}

	@Test
	public void CreateComicTest() throws Exception {
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/comic/createComic");
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		mockRequest.content(this.mapper.writeValueAsString(testComic));
		mockRequest.accept(MediaType.APPLICATION_JSON);

		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(comicDTO));
		this.mock.perform(mockRequest)
				.andExpect(matchStatus).andExpect(matchContent);
		
	}

	@Test
	public void DeleteComicTest() throws Exception {
		this.mock.perform(request(HttpMethod.DELETE, "/comic/deleteComic/" + this.id)).andExpect(status().isNoContent());
	}

	@Test
	public void ReadAllComicsTest() throws Exception {
		List<ComicDTO> comicList = new ArrayList<>();
		comicList.add(this.comicDTO);

		String content = this.mock.perform(request(HttpMethod.GET, "/comic/getAll").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals(this.mapper.writeValueAsString(comicList), content);
	}

	@Test
	public void UpdateComicTest() throws Exception {
		Comic newComic = new Comic("Dennis the Menace", "Bean", "James Manor", 5);
		Comic updatedComic = new Comic(newComic.getTitle(), newComic.getPublisher(), newComic.getWriter(), newComic.getIssue());
		updatedComic.setId(this.id);

		String result = this.mock
				.perform(request(HttpMethod.PUT, "/comic/updateComic?id=" + this.id).accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(newComic)))
				.andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();

		assertEquals(this.mapper.writeValueAsString(this.mapToDTO(updatedComic)), result);
	}
	
}

