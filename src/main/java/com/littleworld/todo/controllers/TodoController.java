
package com.littleworld.todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.littleworld.todo.model.Todo;
import com.littleworld.todo.services.TodoService;


@Controller
public class TodoController {

    @Autowired  private TodoService todoService;

    @RequestMapping(value="/todo", method=RequestMethod.GET)
    public String todoForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "todoForm";
    }

    @RequestMapping(value="/todo", method=RequestMethod.POST)
    public String todoSubmit(Todo todo) {
        todoService.save(todo);
        return "redirect:todos";
    }

   @RequestMapping(value="/todo/{index}", method=RequestMethod.GET)
    public String todoFindById(@PathVariable int index, Model model) {
        Todo todo = todoService.findOne(index) ;
        model.addAttribute("todo", todo);
        return "todoView";
    }

    @RequestMapping(value="/todos", method=RequestMethod.GET)
    public String todoGetAll(Model model) {
        Iterable<Todo> todos = todoService.findAll();
        model.addAttribute("todos", todos);
        return "todoList";
    }

    //curl  http://localhost:8080/todo/listTodo
    @ResponseBody
    @RequestMapping(value = "/listTodo", method = RequestMethod.GET)
    public Iterable<Todo> findAllTodos() {
      return todoService.findAll();
    }
}

