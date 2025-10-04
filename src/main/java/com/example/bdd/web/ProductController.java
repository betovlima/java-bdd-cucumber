package com.example.bdd.web;

import com.example.bdd.product.Product;
import com.example.bdd.product.ProductInput;
import com.example.bdd.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Produtos", description = "Operações de CRUD de produtos")
@RestController
@RequestMapping("/api/produtos")
public class ProductController {
    private final ProductService service;
    public ProductController(ProductService service) { this.service = service; }

    @PostMapping("/criar") // <- use value/path, não 'name'
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cria um produto")
    public Product create(@RequestBody @Valid ProductInput in) {
        return service.create(in);
    }

    @GetMapping("/ler/{id}")
    @Operation(summary = "Ler um produto")
    public Product get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar um produto")
    public Product update(@PathVariable Long id, @RequestBody @Valid ProductInput in) {
        return service.update(id, in);
    }

    @DeleteMapping("/deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Excluir um produto")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}