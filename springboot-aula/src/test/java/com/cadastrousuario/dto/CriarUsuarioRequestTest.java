package com.cadastrousuario.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("CriarUsuarioRequest unit tests")
class CriarUsuarioRequestTest {

    private static final ValidatorFactory VALIDATOR_FACTORY = Validation.buildDefaultValidatorFactory();
    private static final Validator VALIDATOR = VALIDATOR_FACTORY.getValidator();

    @AfterAll
    static void tearDown() {
        VALIDATOR_FACTORY.close();
    }

    @Test
    @DisplayName("Should keep valid request data")
    void criarUsuarioRequestCase1() {
        CriarUsuarioRequest request = new CriarUsuarioRequest();
        request.setNome("Guilherme");
        request.setEmail("guilherme@gmail.com");
        request.setIdade(25);
        request.setEndereco("Rua sei la");
        request.setTelefone("81999999999");

        assertEquals("Guilherme", request.getNome());
        assertEquals("guilherme@gmail.com", request.getEmail());
        assertEquals(25, request.getIdade());
        assertEquals("Rua sei la", request.getEndereco());
        assertEquals("81999999999", request.getTelefone());
        assertTrue(VALIDATOR.validate(request).isEmpty());
    }

    @Test
    @DisplayName("Should fail validation when fields are invalid")
    void criarUsuarioRequestCase2() {
        CriarUsuarioRequest request = new CriarUsuarioRequest();
        request.setNome("");
        request.setEmail("email-invalido");
        request.setIdade(-1);
        request.setEndereco("");
        request.setTelefone("");

        Set<ConstraintViolation<CriarUsuarioRequest>> violations = VALIDATOR.validate(request);

        assertEquals(5, violations.size());
    }
}
