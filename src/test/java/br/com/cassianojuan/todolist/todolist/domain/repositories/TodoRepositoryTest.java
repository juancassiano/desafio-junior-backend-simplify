package br.com.cassianojuan.todolist.todolist.domain.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import br.com.cassianojuan.todolist.todolist.domain.entity.Todo;
import br.com.cassianojuan.todolist.todolist.domain.repository.TodoRepository;

import static br.com.cassianojuan.todolist.todolist.domain.common.TodoConstant.*;


@TestPropertySource(locations = "classpath:application.yml")
@DataJpaTest
public class TodoRepositoryTest {
  
  @Autowired
  private TodoRepository todoRepository;


  @DisplayName("Given Valid Todo Object when Save method is call should return Created Todo")
  @Test
  void testGivenTodoObject_whenCreated_thenReturnCreatedTodo(){
    Todo savedTodo = todoRepository.save(VALID_TODO);

    assertNotNull(savedTodo);
    assertTrue(savedTodo.getId()>0);
  }

  @DisplayName("Given Empty Todo List should Return No Todo")
  @Test
  public void test_empty_todo_list_returned_when_no_todos_present() {
      List<Todo> todos = todoRepository.findAll();

      assertTrue(todos.isEmpty());
  }

  @DisplayName("Given an ID Todo should Update")
  @Test
  public void test_update_todo() {
      Todo savedTodo = todoRepository.save(VALID_TODO);
      long todoId = savedTodo.getId();

      savedTodo.setDescricao("Updated Description");
      Todo updatedTodo = todoRepository.save(savedTodo);

      assertNotNull(updatedTodo);
      assertEquals(todoId, updatedTodo.getId());
      assertEquals("Updated Description", updatedTodo.getDescricao());
  }

  @DisplayName("Given an ID Todo should Remove this TODO")
  @Test
    public void test_delete_todo_by_id() {
      todoRepository.deleteById(VALID_TODO.getId());

      assertFalse(todoRepository.existsById(VALID_TODO.getId()));
    }


  @DisplayName("Given an ID Todo should Return this TODO")
  @Test
    public void test_retrieve_todo_by_id() {
        Todo savedTodo = todoRepository.save(VALID_TODO);
        long todoId = savedTodo.getId();

        Todo retrievedTodo = todoRepository.findById(todoId).orElse(null);

        assertNotNull(retrievedTodo);
        assertEquals(todoId, retrievedTodo.getId());
    }
  @DisplayName("When List method is call should return a Todo List")
  @Test
  void testGiven_whenList_thenReturnTodoList(){
    assertNotNull(TODOS);
    assertEquals(2, TODOS.size());
  }
}
