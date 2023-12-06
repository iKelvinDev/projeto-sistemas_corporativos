package br.edu.ifrn.vendasestoque.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.ifrn.vendasestoque.domain.fabricante.Fabricante;
import br.edu.ifrn.vendasestoque.repository.FabricanteRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("fabricantes")
public class FabricanteController {
    @Autowired
    private FabricanteRepository repository;

    @PostMapping
    @Transactional

    public ResponseEntity<Object> cadastrar(@RequestBody @Valid Fabricante fabricante, 
                                            UriComponentsBuilder uriBuilder){
        Fabricante fabricanteLocal = repository.save(fabricante);
        var uri = uriBuilder.path("/fabricante/{id}").buildAndExpand(fabricanteLocal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fabricante> detalhar(@PathVariable Long id) {
        Fabricante fabricante = repository.getReferenceById(id);
        return ResponseEntity.ok(fabricante);
    }

    @GetMapping
    public ResponseEntity<Page<Fabricante>> listar(@PageableDefault(size=4, 
                                        sort = {"nome"}) Pageable paginacao){
        var fabricante = repository.findAll(paginacao);
        return ResponseEntity.ok(fabricante);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> excluir(@PathVariable Long id){
        var fabricante = repository.getReferenceById(id);
        repository.delete(fabricante);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Fabricante> atualizar(@PathVariable Long id, @RequestBody @Valid Fabricante fabricante){
        Optional<Fabricante> fabricanteOptional = repository.findById(id);
    
        if (fabricanteOptional.isPresent()) {
            Fabricante fabricanteLocal = fabricanteOptional.get();
            fabricanteLocal.setNome(fabricante.getNome());
            repository.save(fabricanteLocal);
            return ResponseEntity.ok(fabricanteLocal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
