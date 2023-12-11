package com.tasks.dailytasks;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@SpringBootTest
@AutoConfigureMockMvc
class DailytasksApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testWelcomePage() throws Exception {
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name("welcome"));
	}

	@Test
	void testGetAllTasks() throws Exception{
		mockMvc.perform(get("/tasks"))
			.andExpect(status().isOk())
			.andExpect(view().name("tasks"));
	}

	@Test
	void testAddNewTaskForm() throws Exception{
		mockMvc.perform(get("/add"))
			.andExpect(status().isOk())
			.andExpect(view().name("add"));
	}

	@Test
	void testAddNewTask() throws Exception {
		mockMvc.perform(post("/add"))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/tasks"));
	}

	@Test
	void testUpdateTaskForm() throws Exception {
		mockMvc.perform(get("/update"))
			.andExpect(status().isOk())
			.andExpect(view().name("update"));
	}

	@Test
	void testUpdateSelectedTask() throws Exception {
	}

	

}
