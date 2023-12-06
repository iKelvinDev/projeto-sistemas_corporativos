CREATE TABLE permissao (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE usuario_permissao (
    id INT PRIMARY KEY AUTO_INCREMENT,
    usuario_id INT,
    permissao_id INT,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (permissao_id) REFERENCES permissao(id)
);

INSERT INTO permissao (nome) VALUES ('ROLE_ADMIN');
INSERT INTO permissao (nome) VALUES ('ROLE_USER');