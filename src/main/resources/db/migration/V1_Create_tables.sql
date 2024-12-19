-- Habilitar suporte a UUID no MySQL 8+
SET SESSION sql_mode = 'ANSI_QUOTES';

-- Criação da tabela "usuarios"
CREATE TABLE usuarios (
                          id CHAR(36) PRIMARY KEY,
                          nome VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL UNIQUE,
                          senha VARCHAR(255) NOT NULL
);

-- Criação da tabela "cursos"
CREATE TABLE cursos (
                        id CHAR(36) PRIMARY KEY,
                        nome VARCHAR(255) NOT NULL,
                        categoria VARCHAR(255) NOT NULL
);

-- Criação da tabela "perfis"
CREATE TABLE perfis (
                        id CHAR(36) PRIMARY KEY,
                        nome VARCHAR(255) NOT NULL,
                        usuario_id CHAR(36),
                        FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- Criação da tabela "topicos"
CREATE TABLE topicos (
                         id CHAR(36) PRIMARY KEY,
                         titulo VARCHAR(255) NOT NULL,
                         mensagem TEXT NOT NULL,
                         data_criacao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         status VARCHAR(50),
                         autor_id CHAR(36),
                         curso_id CHAR(36),
                         FOREIGN KEY (autor_id) REFERENCES perfis(id) ON DELETE SET NULL,
                         FOREIGN KEY (curso_id) REFERENCES cursos(id) ON DELETE SET NULL
);

-- Criação da tabela "respostas"
CREATE TABLE respostas (
                           id CHAR(36) PRIMARY KEY,
                           mensagem TEXT NOT NULL,
                           topico_id CHAR(36),
                           solucao BOOLEAN NOT NULL DEFAULT FALSE,
                           FOREIGN KEY (topico_id) REFERENCES topicos(id) ON DELETE CASCADE
);
