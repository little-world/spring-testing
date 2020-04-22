
package com.littleworld.todo.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.littleworld.todo.model.Todo;
import com.littleworld.todo.services.TodoService;

@RunWith(SpringRunner.class)

@DataJpaTest
public class TodoJpaTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private TodoService todoService;

	@Test
	public void testTodos() throws Exception {

		entityManager.persist(new Todo(0, "task0"));
		Iterable<Todo> all = todoService.findAll();

		assertThat(all).extracting(Todo::getTask).containsOnly("task0");

	
	}
}
