package com.littleworld.todo.controllers;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.littleworld.todo.model.Todo;
import com.littleworld.todo.services.TodoService;

@RunWith(SpringRunner.class)
@WebMvcTest(TodoController.class)
public class TodoStandaloneControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TodoService todoServiceMock;

	@Test
	public void testTodos() throws Exception {
		List<Todo> todos = new ArrayList<Todo>();
		todos.add(new Todo(0, "task0"));
		todos.add(new Todo(1, "task1"));

		when(todoServiceMock.findAll()).thenReturn(todos);

		mockMvc.perform(get("/todos")).andDo(print()).andExpect(status().isOk()).andExpect(view().name("todoList"))
				.andExpect(model().attribute("todos", hasSize(2)))

				.andExpect(model().attribute("todos",
						hasItem(allOf(hasProperty("id", is(0)), hasProperty("task", is("task0"))))))
				.andExpect(model().attribute("todos",
						hasItem(allOf(hasProperty("id", is(1)), hasProperty("task", is("task1"))))));
	}

	@Test
	public void testTodoRest() throws Exception {
		List<Todo> todos = new ArrayList<Todo>();
		todos.add(new Todo(0, "task0"));
		todos.add(new Todo(1, "task1"));

		when(todoServiceMock.findAll()).thenReturn(todos);

		mockMvc.perform(get("/listTodo")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))

				.andExpect(jsonPath("$[0].id", is(0))).andExpect(jsonPath("$[0].task", is("task0")))
				.andExpect(jsonPath("$[1].id", is(1))).andExpect(jsonPath("$[1].task", is("task1")));

		verify(todoServiceMock, times(1)).findAll();
	}
}
