package com.littleworld.todo.controllers;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.littleworld.todo.model.*;

public class RestTemplateTest {

	@Test
	public void test() {
		
		RestTemplate restTemplate = new RestTemplate();
		String todoResourceUrl = "http://localhost:8080/listTodo";
		String result = restTemplate.getForObject(todoResourceUrl, String.class);
		System.out.println("str "+ result);

		Todo[] todos = restTemplate.getForObject(todoResourceUrl, Todo[].class);
		System.out.println("[] " + todos);

		List<Todo> todoList	= restTemplate.getForObject(todoResourceUrl, List.class);
		System.out.println("list " + todoList);

		//assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	
	
}
