package br.edu.ifrn.vendasestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrn.vendasestoque.domain.cliente.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
  
}