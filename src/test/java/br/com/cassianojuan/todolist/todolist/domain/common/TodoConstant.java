package br.com.cassianojuan.todolist.todolist.domain.common;

import java.util.Arrays;
import java.util.List;

import br.com.cassianojuan.todolist.todolist.domain.entity.Todo;

public class TodoConstant {
  public static final Todo VALID_TODO = new Todo(1L,"Tarefa 1", "Descricao 1", false, 1);
  public static final Todo INVALID_TODO = new Todo("", "", false, 0);

  public static final Todo JAVA = new Todo("Java", "Aprender Java", false, 2);
  public static final Todo SPRING = new Todo("Spring", "Aprender Spring", false, 2);

  public static final List<Todo> TODOS= Arrays.asList(JAVA,SPRING);

}
