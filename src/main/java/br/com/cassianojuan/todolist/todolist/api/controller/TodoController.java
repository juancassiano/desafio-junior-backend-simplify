package br.com.cassianojuan.todolist.todolist.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import br.com.cassianojuan.todolist.todolist.api.dtos.TodoDtoInput;
import br.com.cassianojuan.todolist.todolist.api.dtos.TodoDtoModel;
import br.com.cassianojuan.todolist.todolist.api.mapper.TodoMapper;
import br.com.cassianojuan.todolist.todolist.domain.entity.Todo;
import br.com.cassianojuan.todolist.todolist.domain.exceptions.TodoNaoEncontradoException;
import br.com.cassianojuan.todolist.todolist.domain.service.TodoService;
import jakarta.validation.Valid;

@RestController()
@RequestMapping("/todos")
public class TodoController {
  
  private TodoService todoService;
  private TodoMapper todoMapper;

  public TodoController(TodoService todoService, TodoMapper todoMapper) {
    this.todoService = todoService;
    this.todoMapper = todoMapper;
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public TodoDtoModel create(@RequestBody @Valid TodoDtoInput todoInput){
    Todo createdTodo = todoService.create(todoMapper.toEntity(todoInput));
    return todoMapper.toModel(createdTodo);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public CollectionModel<TodoDtoModel> list(){
    List<Todo> todos = todoService.list();
    List<TodoDtoModel> todoDtoModels = todos.stream()
      .map(todoMapper::toModel)
      .collect(Collectors.toList());

      return CollectionModel.of(todoDtoModels);
  }

  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public TodoDtoModel find(@PathVariable Long id){
    try{
      Todo todoAtual = todoService.findTodo(id);
      return todoMapper.toModel(todoAtual);
    }catch(TodoNaoEncontradoException e){
      throw new TodoNaoEncontradoException(id);
    }
  }

  @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public TodoDtoModel update(@PathVariable Long id, @RequestBody @Valid TodoDtoInput todoInput){
     try{
      Todo updatedTodo = todoMapper.toEntity(todoInput);
      Todo updatedTodoResult = todoService.update(id, updatedTodo);
      return todoMapper.toModel(updatedTodoResult);
    } catch(TodoNaoEncontradoException e){
      throw new TodoNaoEncontradoException(id);
    }
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void delete(@PathVariable Long id){
    todoService.delete(id);
  }
}
