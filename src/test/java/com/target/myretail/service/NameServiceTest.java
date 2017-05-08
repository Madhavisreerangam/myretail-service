package com.target.myretail.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Integration/system testcases for name service. Ignoring as of now because
 * they expect data in mongo db
 * 
 * @author madhavi
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NameServiceTest {
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testWelcome() throws Exception {
		mockMvc.perform(get("/name")).andExpect(status().isOk())
				.andExpect(content().contentType("text/plain;charset=UTF-8"));

	}

	@Test
	@Ignore
	public void testGetName() throws Exception {
		mockMvc.perform(get("/name/get/1")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.name").value("The Big Lebowski"));
	}

	@Test
	@Ignore
	public void testGetNameInvalidRequest() throws Exception {
		mockMvc.perform(get("/name/get/abc")).andExpect(status().is4xxClientError());
	}

	@Test
	public void testGetNameNotFound() throws Exception {
		mockMvc.perform(get("/name/get/11")).andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("DefaultName"));
	}

}
