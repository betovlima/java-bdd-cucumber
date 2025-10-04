package com.example.bdd.product;

import java.math.BigDecimal;

public class Product {
    private Long id;
    private String nome;
    private BigDecimal preco;
    private String descricao;

    public Product() {}
    public Product(Long id, String nome, BigDecimal preco, String descricao) {
        this.id = id; this.nome = nome; this.preco = preco; this.descricao = descricao;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public BigDecimal getPreco() { return preco; }
    public String getDescricao() { return descricao; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
