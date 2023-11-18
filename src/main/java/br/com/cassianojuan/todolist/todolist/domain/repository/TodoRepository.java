package br.com.cassianojuan.todolist.todolist.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cassianojuan.todolist.todolist.domain.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
  
}
