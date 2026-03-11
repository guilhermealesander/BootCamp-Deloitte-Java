package com.cadastrousuario.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("AlterarNomeRequest unit tests")
class AlterarNomeRequestTest {

    private static final ValidatorFactory VALIDATOR_FACTORY = Validation.buildDefaultValidatorFactory();
    private static final Validator VALIDATOR = VALIDATOR_FACTORY.getValidator();

    @AfterAll
    static void tearDown() {
        VALIDATOR_FACTORY.close();
    }

    @Test
    @DisplayName("Should keep valid name")
    void alterarNomeRequestCase1() {
        AlterarNomeRequest request = new AlterarNomeRequest();
        request.setNovoNome("Guilherme");

        assertEquals("Guilherme", request.getNovoNome());
        assertTrue(VALIDATOR.validate(request).isEmpty());
    }

    @Test
    @DisplayName("Should fail validation when name is blank")
    void alterarNomeRequestCase2() {
        AlterarNomeRequest request = new AlterarNomeRequest();
        request.setNovoNome("");

        assertFalse(VALIDATOR.validate(request).isEmpty());
    }
}
