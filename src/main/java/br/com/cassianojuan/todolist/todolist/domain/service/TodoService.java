package br.com.cassianojuan.todolist.todolist.domain.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.cassianojuan.todolist.todolist.domain.entity.Todo;
import br.com.cassianojuan.todolist.todolist.domain.exceptions.TodoEmUsoException;
import br.com.cassianojuan.todolist.todolist.domain.exceptions.TodoNaoEncontradoException;
import br.com.cassianojuan.todolist.todolist.domain.repository.TodoRepository;

@Service
public class TodoService {

  private TodoRepository todoRepository;

  public static final String MSG_TODO_EM_USO = "TODO de ID %d está em uso e não pode ser removido";
  
  
  public TodoService(TodoRepository todoRepository) {
    this.todoRepository = todoRepository; 
  }

  public Todo create(Todo todo){
    return todoRepository.save(todo);
  }

  public Todo update(Long id, Todo updatedTodo){
    Todo existingTodo = findTodo(id);
    existingTodo.setNome(updatedTodo.getNome());
    existingTodo.setDescricao(updatedTodo.getDescricao());
    existingTodo.setRealizado(updatedTodo.isRealizado());
    existingTodo.setPrioridade(updatedTodo.getPrioridade());

    return todoRepository.save(existingTodo);

  }

  public List<Todo> list(){
    Sort list = Sort.by("prioridade").descending().and(Sort.by("nome").ascending());

    return todoRepository.findAll(list);
  }


  public void delete(Long id){
    try{
    todoRepository.deleteById(id);
    }catch(EmptyResultDataAccessException e){
            throw new TodoNaoEncontradoException(id);
        }
        catch(DataIntegrityViolationException e){
            throw new TodoEmUsoException(
              String.format(MSG_TODO_EM_USO, id));
        }
  }


  public Todo findTodo(Long id){
    return todoRepository.findById(id).orElseThrow(
      () -> new TodoNaoEncontradoException(id)
    );
  }

  
}
