create table item_venda (
    id INT AUTO_INCREMENT PRIMARY KEY,
    produto_id INT NOT NULL,
    venda_id INT NOT NULL,
    preco DECIMAL NOT NULL,
    quantidade INT NOT NULL,    
    FOREIGN KEY (produto_id) REFERENCES produto(id),
    FOREIGN KEY (venda_id) REFERENCES venda(id)
);