package br.edu.ifrn.vendasestoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import br.edu.ifrn.vendasestoque.domain.produto.Produto;
import br.edu.ifrn.vendasestoque.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Produto> cadastrar(@RequestBody Produto produto) {
        Produto produtoLocal = produtoRepository.save(produto);
        return ResponseEntity.ok(produtoLocal);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> detalhar(@PathVariable Long id) {
        var produto = produtoRepository.findById(id);
        return ResponseEntity.ok(produto);
    }

    @GetMapping
    public ResponseEntity<Page<Produto>> listar(@PageableDefault(size = 4, sort = { "nome" }) Pageable paginacao) {
        var produtos = produtoRepository.findAll(paginacao);
        return ResponseEntity.ok(produtos);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var produto = produtoRepository.getReferenceById(id);
        produtoRepository.delete(produto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        var produtoLocal = produtoRepository.getReferenceById(id);
        produtoLocal.setNome(produto.getNome());
        produtoLocal.setDescricao(produto.getDescricao());
        produtoLocal.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        produtoLocal.setPreco(produto.getPreco());
        produtoRepository.save(produtoLocal);
        return ResponseEntity.ok(produtoLocal);
    }
}