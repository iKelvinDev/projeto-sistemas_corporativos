create table produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(250) NOT NULL,
    quantidadeEstoque INT NOT NULL,
    preco DECIMAL NOT NULL
);