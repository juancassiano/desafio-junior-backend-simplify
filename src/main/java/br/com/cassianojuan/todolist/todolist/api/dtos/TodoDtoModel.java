package br.com.cassianojuan.todolist.todolist.api.dtos;

import org.springframework.hateoas.RepresentationModel;

public class TodoDtoModel extends RepresentationModel<TodoDtoModel> {
  private Long id; 
  private String nome; 
  private String descricao; 
  private boolean realizado; 
  private int prioridade;

  public TodoDtoModel(){

  }
  
  public TodoDtoModel(Long id, String nome, String descricao, boolean realizado, int prioridade) {
  }
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public String getDescricao() {
    return descricao;
  }
  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }
  public boolean isRealizado() {
    return realizado;
  }
  public void setRealizado(boolean realizado) {
    this.realizado = realizado;
  }
  public int getPrioridade() {
    return prioridade;
  }
  public void setPrioridade(int prioridade) {
    this.prioridade = prioridade;
  }

  
}
