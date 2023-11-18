package br.com.cassianojuan.todolist.todolist.api.dtos;

public class TodoDtoInput{
  String nome; 
  String descricao; 
  boolean realizado; 
  int prioridade;

  
  public TodoDtoInput() {
  }

  
  public TodoDtoInput(String nome, String descricao, boolean realizado, int prioridade) {
    this.nome = nome;
    this.descricao = descricao;
    this.realizado = realizado;
    this.prioridade = prioridade;
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
