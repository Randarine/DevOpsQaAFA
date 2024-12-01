package br.facens.AFDevOpsQA.entity.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import br.facens.AFDevOpsQA.entity.*;


public class PessoaTest {

    @Test
    public void testPessoaInitialization() {
        Pessoa pessoa = new Pessoa();
        assertNotNull(pessoa, "Pessoa object should be initialized");
    }

}
