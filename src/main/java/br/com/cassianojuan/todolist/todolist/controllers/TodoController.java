package br.com.cassianojuan.todolist.todolist.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cassianojuan.todolist.todolist.entity.Todo;
import br.com.cassianojuan.todolist.todolist.service.TodoService;
import jakarta.validation.Valid;

@RestController()
@RequestMapping("/todos")
public class TodoController {
  
  private TodoService todoService;

  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  @PostMapping
  List<Todo> create(@RequestBody @Valid Todo todo){
    return todoService.create(todo);
  }

  @GetMapping
  List<Todo> list(){
    return todoService.list();
  }

  @PutMapping()
  List<Todo> update(@RequestBody Todo todo){
    return todoService.update(todo);
  }

  @DeleteMapping("/{id}")
  List<Todo> delete(@PathVariable Long id){
    return todoService.delete(id);
  }
}
