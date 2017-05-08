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
 * Integration/system testcases for product REST service. Ignoring as of now because
 * they expect data in mongo db
 * 
 * @author madhavi
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testWelcome() throws Exception {
		mockMvc.perform(get("/product")).andExpect(status().isOk())
				.andExpect(content().contentType("text/plain;charset=UTF-8"));

	}

	@Ignore
	@Test
	public void testGetProduct() throws Exception {
		mockMvc.perform(get("/product/get/1")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.id").value("1")).andExpect(jsonPath("$.name").value("The Big Lebowski"))
				.andExpect(jsonPath("$.currentPrice.value").value("13.49"));
	}

}
