package br.com.cassianojuan.todolist.todolist.domain.exceptions;

public class TodoEmUsoException extends RuntimeException{
  
  public TodoEmUsoException(String mensagem){
    super(mensagem);
  }
}
