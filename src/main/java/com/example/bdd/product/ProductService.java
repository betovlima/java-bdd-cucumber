package com.example.bdd.product;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) { this.repo = repo; }

    public Product create(@Valid ProductInput in) {
        Product p = new Product(null, in.nome(), in.preco(), in.descricao());
        return repo.save(p);
    }

    public Product get(Long id) {
        return repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    public Product update(Long id, @Valid ProductInput in) {
        Product current = get(id);
        current.setNome(in.nome());
        current.setPreco(in.preco());
        current.setDescricao(in.descricao());
        return repo.save(current);
    }

    public void delete(Long id) {
        if (repo.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
        repo.deleteById(id);
    }

    public void deleteAll() { repo.deleteAll(); }
}