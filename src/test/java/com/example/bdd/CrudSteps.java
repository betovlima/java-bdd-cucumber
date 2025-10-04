package com.example.bdd;

import com.example.bdd.product.InMemoryProductRepository;
import com.example.bdd.product.Product;
import com.example.bdd.product.ProductInput;
import io.cucumber.java.Before;
import io.cucumber.java.pt.*;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.math.BigDecimal;
import java.util.Map;

public class CrudSteps {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate rest;

    @Autowired
    private InMemoryProductRepository repo;

    private ResponseEntity<Product> lastProductResponse;
    private ResponseEntity<Map> lastErrorResponse;
    private Long storedId;

    private String baseUrl() {
        return "http://localhost:" + port + "/api/produtos";
    }

    @Before
    public void reset() {
        repo.deleteAll();
        lastProductResponse = null;
        lastErrorResponse = null;
        storedId = null;
    }

    @Dado("que o catálogo de produtos está vazio")
    public void catalogo_vazio() {
        repo.deleteAll();
        Assertions.assertThat(repo.findAll()).isEmpty();
    }

    @Quando("eu crio um produto com nome {string} e preco {double}")
    public void crio_produto(String nome, Double preco) {
        ProductInput in = new ProductInput(nome, BigDecimal.valueOf(preco), null);
        lastProductResponse = rest.postForEntity(baseUrl() + "/criar", in, Product.class);
    }

    @Quando("eu busco o produto pelo id guardado")
    public void busco_por_id_guardado() {
        Assertions.assertThat(storedId).isNotNull();
        lastProductResponse = rest.getForEntity(baseUrl() + "/ler/" + storedId, Product.class);
    }

    @Quando("eu atualizo o produto guardado para nome {string} e preco {double}")
    public void atualizo_produto_guardado(String novoNome, Double novoPreco) {
        Assertions.assertThat(storedId).isNotNull();
        ProductInput in = new ProductInput(novoNome, BigDecimal.valueOf(novoPreco), null);
        HttpEntity<ProductInput> entity = new HttpEntity<>(in);
        lastProductResponse = rest.exchange(
                baseUrl() + "/atualizar/" + storedId, HttpMethod.PUT, entity, Product.class);
    }

    @Quando("eu deleto o produto guardado")
    public void deleto_produto() {
        Assertions.assertThat(storedId).isNotNull();
        rest.delete(baseUrl() + "/deletar/" + storedId);
        lastProductResponse = new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @E("eu guardo o id retornado")
    public void guardo_id() {
        Assertions.assertThat(lastProductResponse).isNotNull();
        Assertions.assertThat(lastProductResponse.getBody()).isNotNull();
        storedId = lastProductResponse.getBody().getId();
        Assertions.assertThat(storedId).isNotNull();
    }

    @Quando("eu crio um produto sem nome e com preco {double}")
    public void eu_crio_um_produto_sem_nome_e_com_preco(Double preco) {
        ProductInput in = new ProductInput(null, BigDecimal.valueOf(preco), null);
        lastProductResponse = rest.postForEntity(baseUrl() + "/criar", in, Product.class);
    }

    @Entao("a resposta deve ter status {int}")
    public void resposta_status(int status) {
        if (lastProductResponse != null) {
            Assertions.assertThat(lastProductResponse.getStatusCode().value()).isEqualTo(status);
        } else if (lastErrorResponse != null) {
            Assertions.assertThat(lastErrorResponse.getStatusCode().value()).isEqualTo(status);
        } else {
            Assertions.fail("Nenhuma resposta disponível para validar status");
        }
    }

    @Entao("a resposta deve ter status {int} e nome {string} e preco {double}")
    public void resposta_status_campos(int status, String nome, Double preco) {
        Assertions.assertThat(lastProductResponse).isNotNull();
        Assertions.assertThat(lastProductResponse.getStatusCode().value()).isEqualTo(status);
        Product body = lastProductResponse.getBody();
        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getNome()).isEqualTo(nome);
        Assertions.assertThat(body.getPreco()).isEqualByComparingTo(BigDecimal.valueOf(preco));
    }

}
