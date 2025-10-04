// src/test/java/com/example/demo/bdd/CucumberSpringConfiguration.java
package com.example.bdd;

import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;

@CucumberContextConfiguration
@SpringBootTest(
        classes = App.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("test")
public class CucumberSpringConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    // Hook do Cucumber: roda antes de cada cenário
    @Before
    public void verifySpringContextIsUp() {
        Assertions.assertThat(applicationContext)
                .as("Spring ApplicationContext deve estar disponível nos steps")
                .isNotNull();
    }

    // Beans visíveis apenas nos testes/BDD
    @TestConfiguration
    static class TestBeans {
        @Bean
        Clock fixedClock() {
            return Clock.fixed(Instant.parse("2024-01-01T00:00:00Z"), ZoneOffset.UTC);
        }
    }
}
