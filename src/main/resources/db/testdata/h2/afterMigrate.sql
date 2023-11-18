-- Liquibase formatted SQL

-- Changeset YOUR_AUTHOR_NAME:afterMigrate.sql
-- Condition: databasechangelog.dbms = 'mysql'

-- Remove a linha a seguir, já que o H2 não suporta SET foreign_key_checks = 0;
-- SET foreign_key_checks = 0;

DELETE FROM todos;

-- Remove a linha a seguir, já que o H2 não suporta SET foreign_key_checks = 1;
-- SET foreign_key_checks = 1;

ALTER TABLE todos ALTER COLUMN id RESTART WITH 1;

INSERT INTO todos (nome, descricao, realizado, prioridade) VALUES ('Estudar Java', 'Estudar programação Java para melhorar minhas habilidades.', false, 2);
INSERT INTO todos (nome, descricao, realizado, prioridade) VALUES ('Fazer compras', 'Comprar itens essenciais como leite, pão e ovos.', false, 1);
INSERT INTO todos (nome, descricao, realizado, prioridade) VALUES ('Ir à academia', 'Fazer exercícios físicos para manter uma vida saudável.', false, 3);
INSERT INTO todos (nome, descricao, realizado, prioridade) VALUES ('Ler livro', 'Ler um livro interessante para relaxar e aprender algo novo.', false, 2);
INSERT INTO todos (nome, descricao, realizado, prioridade) VALUES ('Organizar armário', 'Arrumar roupas e organizar o armário.', false, 1);
