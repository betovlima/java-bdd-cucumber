// src/test/java/com/example/demo/bdd/LoginSteps.java
package com.example.bdd;

import com.example.bdd.auth.AuthService;
import com.example.bdd.auth.AuthService.LoginResult;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginSteps {

    @Autowired
    private AuthService authService;

    private LoginResult resultado;

    @Before
    public void init() {
        resultado = null;
    }

    @Dado("que estou na página de login")
    public void que_estou_na_pagina_de_login() {
        // passo ilustrativo
    }

    @Quando("eu informo o usuário {string} e a senha {string}")
    public void eu_informo_o_usuario_e_a_senha(String user, String pass) {
        String u = toNullIfToken(user);
        String p = toNullIfToken(pass);
        resultado = authService.login(u, p);
    }

    @Entao("o resultado deve ser {string}")
    public void o_resultado_deve_ser(String esperado) {
        LoginResult esperadoEnum = switch (esperado.trim().toUpperCase()) {
            case "SUCESSO" -> LoginResult.SUCCESS;
            case "CREDENCIAIS_INVALIDAS", "CREDENCIAIS_INVÁLIDAS" -> LoginResult.BAD_CREDENTIALS;
            case "USUARIO_NAO_ENCONTRADO", "USUÁRIO_NÃO_ENCONTRADO" -> LoginResult.USER_NOT_FOUND;
            default -> throw new IllegalArgumentException("Resultado esperado inválido: " + esperado);
        };
        Assertions.assertThat(resultado).isEqualTo(esperadoEnum);
    }

    private String toNullIfToken(String value) {
        if (value == null) return null;
        String up = value.trim().toUpperCase();
        return (up.equals("NULL") || up.equals("<NULL>") || up.equals("(NULL)")
                || up.equals("NULO") || up.equals("<NULO>") || up.equals("(NULO)"))
                ? null : value;
    }
}
