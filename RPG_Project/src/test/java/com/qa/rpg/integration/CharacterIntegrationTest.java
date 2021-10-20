package com.qa.rpg.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.rpg.Character;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:character-schema.sql",
"classpath:character-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class CharacterIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	void testCreate() throws Exception {
		final Character testCharacter = new Character(null, "zhongli", "geo", "polearm");
		String testCharacterAsJSON = this.mapper.writeValueAsString(testCharacter);

		final Character savedCharacter = new Character(2, "zhongli", "geo", "polearm");
		String savedCharacterAsJSON = this.mapper.writeValueAsString(savedCharacter);

		RequestBuilder request = post("/createCharacter").contentType(MediaType.APPLICATION_JSON)
				.content(testCharacterAsJSON);

		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkContent = content().json(savedCharacterAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}
	
	@Test
	void testGetAll() throws Exception {
		String savedCharacterAsJSON = this.mapper
				.writeValueAsString(List.of(new Character(1, "zhongli", "geo", "polearm")));

		RequestBuilder request = get("/getAllCharacters");

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedCharacterAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}
	
	@Test
	void testGetById() throws Exception {
		final Character savedCharacter = new Character(1, "zhongli", "geo", "polearm");
		String savedCharacterAsJSON = this.mapper.writeValueAsString(savedCharacter);

		RequestBuilder request = get("/getCharacter/" + savedCharacter.getId());

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedCharacterAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}
	
	@Test
	void testUpdate() throws Exception {
		final Character testCharacter = new Character(1, "zhongli", "geo", "polearm");
		final String testCharacterAsJSON = this.mapper.writeValueAsString(testCharacter);

		RequestBuilder request = put("/updateCharacter/1").contentType(MediaType.APPLICATION_JSON)
				.content(testCharacterAsJSON);

		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkContent = content().json(testCharacterAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}
	
	@Test
	void testDelete() throws Exception {
		this.mvc.perform(delete("/removeCharacter/1")).andExpect(status().isNoContent());
	}

}
