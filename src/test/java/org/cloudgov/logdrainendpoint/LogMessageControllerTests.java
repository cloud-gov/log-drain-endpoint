package org.cloudgov.logdrainendpoint;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
public class LogMessageControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	LogMessageRepository repository;

	@BeforeEach
	public void before() {
		repository.deleteAll();
	}

	@Test
	public void itSavesAValidLog() throws Exception {
		String msg = "2020-05-28T08:26:44.17-0600 [APP/PROC/WEB/0] ERR 2020-05-28 14:26:44,175 4ed0e3ad-54ec-48b8-6500-7c38 - 107";
		this.mockMvc.perform(post("/").content(msg)).andExpect(status().isOk());
		this.mockMvc.perform(get("/logs")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("4ed0e3ad-54ec-48b8-6500-7c38")))
				.andExpect(content().string(containsString("107")));
	}

	@Test
	public void itDoesNotSaveAnInvalidLog() throws Exception {
		String msg = "test-message";
		this.mockMvc.perform(post("/").content(msg)).andExpect(status().isOk());
		this.mockMvc.perform(get("/logs")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(not(containsString(msg))));
		assertEquals(0, repository.count());
	}

}


