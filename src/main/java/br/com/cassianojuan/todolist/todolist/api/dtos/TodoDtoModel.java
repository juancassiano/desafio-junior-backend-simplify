package br.com.cassianojuan.todolist.todolist.api.dtos;

public record TodoDtoModel(
  Long id, 
  String nome, 
  String descricao, 
  boolean realizado, 
  int prioridade
) {
  
}
