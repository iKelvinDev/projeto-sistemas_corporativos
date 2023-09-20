package br.edu.ifrn.vendasestoque.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.ifrn.vendasestoque.domain.categoria.Categoria;
import br.edu.ifrn.vendasestoque.repository.CategoriaRepository;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("categorias")
public class CategoriaController {
    
    @Autowired
    private CategoriaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody Categoria categoria, UriComponentsBuilder uriBuilder){
        Categoria categoriaLocal = repository.save(categoria); 
        var uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoriaLocal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var categoria = repository.findById(id);
        return ResponseEntity.ok(categoria);
    }

    @GetMapping
    public ResponseEntity<Page<Categoria>> listar(@PageableDefault(size=4, sort = {"nome"}) Pageable paginacao){
        var categorias = repository.findAll(paginacao);
        return ResponseEntity.ok(categorias);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var categoria = repository.getReferenceById(id);
        log.info(categoria);
        repository.delete(categoria);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody Categoria categoria){
        var categoriaLocal = repository.getReferenceById(categoria.getId());
        repository.save(categoria);
        return ResponseEntity.ok(categoriaLocal);
    }

}
