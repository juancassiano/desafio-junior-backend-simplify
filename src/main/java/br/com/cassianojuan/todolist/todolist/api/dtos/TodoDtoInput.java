package br.com.cassianojuan.todolist.todolist.api.dtos;

public record TodoDtoInput(
  String nome, 
  String descricao, 
  boolean realizado, 
  int prioridade
) {
  
}
