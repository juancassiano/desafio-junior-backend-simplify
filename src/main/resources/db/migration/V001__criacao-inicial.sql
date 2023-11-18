create table todos(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(255) NOT NULL,
  descricao VARCHAR(255) NOT NULL,
  realizado BOOLEAN,
  prioridade INT

  primary key(id)
) engine=InnoDB default charset=utf8;
