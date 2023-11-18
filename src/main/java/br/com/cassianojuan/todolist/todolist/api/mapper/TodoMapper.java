package br.com.cassianojuan.todolist.todolist.api.mapper;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

import br.com.cassianojuan.todolist.todolist.api.controllers.TodoController;
import br.com.cassianojuan.todolist.todolist.api.dtos.TodoDtoInput;
import br.com.cassianojuan.todolist.todolist.api.dtos.TodoDtoModel;
import br.com.cassianojuan.todolist.todolist.domain.entity.Todo;

@Component
public class TodoMapper extends RepresentationModelAssemblerSupport<Todo,TodoDtoModel>{
  public TodoMapper() {
        super(TodoController.class, TodoDtoModel.class);
        //TODO Auto-generated constructor stub
    }

public TodoDtoModel toModel(Todo todo) {
    TodoDtoModel todoModel = createModelWithId(todo.getId(), todo);
    
    todoModel.setId(todo.getId());
    todoModel.setNome(todo.getNome());
    todoModel.setDescricao(todo.getDescricao());
    todoModel.setRealizado(todo.isRealizado());
    todoModel.setPrioridade(todo.getPrioridade());

    todoModel.add(linkTo(methodOn(TodoController.class)
        .list()).withRel("todos"));

    return todoModel;
    }

    public Todo toEntity(TodoDtoInput todoDtoinput) {
        return new Todo(
                todoDtoinput.getNome(),
                todoDtoinput.getDescricao(),
                todoDtoinput.isRealizado(),
                todoDtoinput.getPrioridade()
        );
    }

    @Override
    public CollectionModel<TodoDtoModel> toCollectionModel(Iterable<? extends Todo> entities) {
      // TODO Auto-generated method stub
      return super.toCollectionModel(entities).add(linkTo(TodoController.class).withRel("todos"));
    }
}
