package br.com.cassianojuan.todolist.todolist.api.mapper;

import org.springframework.stereotype.Component;

import br.com.cassianojuan.todolist.todolist.api.dtos.TodoDtoInput;
import br.com.cassianojuan.todolist.todolist.api.dtos.TodoDtoModel;
import br.com.cassianojuan.todolist.todolist.domain.entity.Todo;

@Component
public class TodoMapper {
  public TodoDtoModel toModel(Todo todo) {
        return new TodoDtoModel(
                todo.getId(),
                todo.getNome(),
                todo.getDescricao(),
                todo.isRealizado(),
                todo.getPrioridade()
        );
    }

    public Todo toEntity(TodoDtoInput todoDtoinput) {
        return new Todo(
                todoDtoinput.nome(),
                todoDtoinput.descricao(),
                todoDtoinput.realizado(),
                todoDtoinput.prioridade()
        );
    }
}
