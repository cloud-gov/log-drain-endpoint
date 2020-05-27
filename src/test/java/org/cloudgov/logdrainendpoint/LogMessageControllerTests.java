package org.cloudgov.logdrainendpoint;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

	@Test
	public void itSavesARecentLog() throws Exception {
		String msg = "test-message";
		this.mockMvc.perform(post("/").content(msg)).andExpect(status().isOk());
		this.mockMvc.perform(get("/logs")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(msg)));
	}

	@Test
	public void itSavesLongLogs() throws Exception {
		String msg = "This is a long message. This is a long message. This is a long message. " 
				+ "This is a long message. This is a long message. This is a long message. This is a long message. "
				+ "This is a long message. This is a long message. This is a long message. This is a long message. "
				+ "This is a long message. This is a long message. This is a long message. This is a long message. "
				+ "This is a long message. This is a long message. This is a long message. This is a long message. "
				+ "This is a long message. This is a long message. This is a long message. This is a long message.";
		this.mockMvc.perform(post("/").content(msg)).andExpect(status().isOk());
		this.mockMvc.perform(get("/logs")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(msg)));
	}

	@Test
	public void itSavesLogsWithSpecialCharacters() throws Exception {
		String msg = "!@#@#$[]%^%&&#@*&^*(*()+_)<>?}{P:}";
		this.mockMvc.perform(post("/").content(msg)).andExpect(status().isOk());
		this.mockMvc.perform(get("/logs")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(msg)));
	}

}