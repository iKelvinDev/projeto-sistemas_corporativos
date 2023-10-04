create table produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(250) NOT NULL,
    quantidade_estoque INT NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    fabricante_id INT NOT NULL,
    categoria_id INT NOT NULL,
    FOREIGN KEY (categoria_id) REFERENCES categoria(id),
    FOREIGN KEY (fabricante_id) REFERENCES fabricante(id)
);