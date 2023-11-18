package br.com.cassianojuan.todolist.todolist.domain.exceptions;

public class TodoNaoEncontradoException extends RuntimeException{
  private static final long serialVersionUID = 1L;

  public TodoNaoEncontradoException(Long id){
    super(String.format("Não existe todo para o ID %d", id));
  }

}
