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

@DisplayName("ConsultaRequest unit tests")
class ConsultaRequestTest {

    private static final ValidatorFactory VALIDATOR_FACTORY = Validation.buildDefaultValidatorFactory();
    private static final Validator VALIDATOR = VALIDATOR_FACTORY.getValidator();

    @AfterAll
    static void tearDown() {
        VALIDATOR_FACTORY.close();
    }

    @Test
    @DisplayName("Should keep valid appointment request")
    void consultaRequestCase1() {
        ConsultaRequest request = new ConsultaRequest();
        request.setData("2026-03-20");
        request.setHora("14:00");
        request.setMedico("Dr. Alesander");
        request.setEspecialidade("Oftalmologista");

        assertEquals("2026-03-20", request.getData());
        assertEquals("14:00", request.getHora());
        assertEquals("Dr. Alesander", request.getMedico());
        assertEquals("Oftalmologista", request.getEspecialidade());
        assertTrue(VALIDATOR.validate(request).isEmpty());
    }

    @Test
    @DisplayName("Should fail validation when appointment request is blank")
    void consultaRequestCase2() {
        ConsultaRequest request = new ConsultaRequest();
        request.setData("");
        request.setHora("");
        request.setMedico("");
        request.setEspecialidade("");

        assertFalse(VALIDATOR.validate(request).isEmpty());
    }
}
