package br.com.cassianojuan.todolist.todolist.domain.services;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static br.com.cassianojuan.todolist.todolist.domain.common.TodoConstant.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.assertj.core.api.Assertions.*;

import br.com.cassianojuan.todolist.todolist.domain.entity.Todo;
import br.com.cassianojuan.todolist.todolist.domain.exceptions.TodoEmUsoException;
import br.com.cassianojuan.todolist.todolist.domain.exceptions.TodoNaoEncontradoException;
import br.com.cassianojuan.todolist.todolist.domain.repository.TodoRepository;
import br.com.cassianojuan.todolist.todolist.domain.service.TodoService;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;

@ExtendWith(MockitoExtension.class)
public class TodoServicetest {
  
  @Mock
  private TodoRepository todoRepository;

  @InjectMocks
  private TodoService todoService;

    @DisplayName("Should Save a new Todo Object to the repository and Return it")
    @Test
    public void testSaveNewTodo_thenReturnTodo() {

      when(todoRepository.save(VALID_TODO)).thenReturn(VALID_TODO);
      Todo todo = todoService.create(VALID_TODO);
      assertThat(todo).isEqualTo(VALID_TODO);
    }


  @DisplayName("JUnit test for Given Invalid TodoID when FindById Todo then Throw TodoNaoEncontradoException")
  @Test
  public void findTodoWithUnexistingId_ThrowsException(){

        assertThatThrownBy(() -> todoService.findTodo(99L)).isInstanceOf(TodoNaoEncontradoException.class);
  }

@DisplayName("JUnit test for Given Valid TodoID when FindById Todo then Return Todo")
@Test
public void findTodoWithExistingId_ThenReturnTodo(){
    when(todoRepository.findById(VALID_TODO.getId())).thenReturn(Optional.of(VALID_TODO));

    Todo todo = todoService.findTodo(VALID_TODO.getId());

    assertThat(todo).isNotNull()
                    .isEqualTo(VALID_TODO)
                    .hasFieldOrPropertyWithValue("id", VALID_TODO.getId());
}

    @DisplayName("JUnit test for Given TodoID when Delete Todo then do Nothing")
    @Test
    void testGivenTodoID_WhenDeleteTodo_thenDoNothing() {
        
        assertThatCode(() -> todoService.delete(1L)).doesNotThrowAnyException();
    }  

  @DisplayName("JUnit test for Given Invalid TodoID when Delete Todo then Throw TodoNaoEncontradoException")
  @Test
  public void removeTodoWithUnexistingId_ThrowsException(){
        doThrow(EmptyResultDataAccessException.class).when(todoRepository).deleteById(99L);

        assertThatThrownBy(() -> todoService.delete(99L)).isInstanceOf(TodoNaoEncontradoException.class);
  }

  @DisplayName("JUnit test for Given Invalid TodoID when Delete Todo then Throw TodoNaoEncontradoException")
  @Test
  public void removeTodoWithIdInUse_ThrowsException(){
        doThrow(DataIntegrityViolationException.class).when(todoRepository).deleteById(99L);

        assertThatThrownBy(() -> todoService.delete(99L)).isInstanceOf(TodoEmUsoException.class);
  }

  @DisplayName("JUnit test For List Method Should Return a Todo List")
  @Test
  public void listTodos_ReturnsTodos(){
    when(todoRepository.findAll(Sort.by(Sort.Order.desc("prioridade"), Sort.Order.asc("nome")))).thenReturn(TODOS);

    List<Todo>todoList = todoService.list();

    assertThat(todoList)
    .isNotNull()
    .hasSize(2)
    .containsExactlyElementsOf(TODOS);
  }     

}
