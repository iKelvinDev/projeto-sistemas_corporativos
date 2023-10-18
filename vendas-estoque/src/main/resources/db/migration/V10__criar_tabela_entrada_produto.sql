create table entrada_produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    quantidade INT NOT NULL,
    preco DECIMAL NOT NULL,
    produto_id INT NOT NULL,
    fornecedor_id INT NOT NULL,
    FOREIGN KEY (produto_id) REFERENCES produto(id),
    FOREIGN KEY (fornecedor_id) REFERENCES fornecedor(id)
);