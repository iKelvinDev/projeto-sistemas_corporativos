package br.edu.ifrn.vendasestoque.domain.produto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

import br.edu.ifrn.vendasestoque.domain.categoria.Categoria;
import br.edu.ifrn.vendasestoque.domain.fabricante.Fabricante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "produto")
@Table(name = "produto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;
    private String descricao;

    @NotNull
    private int quantidadeEstoque;

    @NotNull
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    
    @ManyToOne
    @JoinColumn(name = "fabricante_id")
    private Fabricante fabricante;

    private boolean ativo;

    public Produto(@Valid ProdutoRequestDTO, produtoDTO) {
        this.nome = produtoDTO.nome();
        this.descricao = produtoDTO.descricao();
        this.quantidadeEstoque = produtoDTO.quantidadeEstoque();
        this.preco = produtoDTO.preco();
        this.categoria = produtoDTO.categoria();
        this.fabricante = produtoDTO.fabricante();
    }

}
