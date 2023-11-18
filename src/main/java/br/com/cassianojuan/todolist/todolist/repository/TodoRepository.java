package br.com.cassianojuan.todolist.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cassianojuan.todolist.todolist.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
  
}
