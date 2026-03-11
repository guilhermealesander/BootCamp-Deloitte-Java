package com.cadastrousuario.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.core.MethodParameter;

import java.lang.reflect.Method;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ApiExceptionHandler unit tests")
class ApiExceptionHandlerTest {

    private final ApiExceptionHandler apiExceptionHandler = new ApiExceptionHandler();

    @Test
    @DisplayName("Should return not found when illegal state happens")
    void handleIllegalStateCase1() {
        IllegalStateException exception = new IllegalStateException("Nenhum usuario cadastrado.");

        ResponseEntity<Map<String, String>> response = apiExceptionHandler.handleIllegalState(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Nenhum usuario cadastrado.", response.getBody().get("erro"));
    }

    @Test
    @DisplayName("Should return bad request with validation errors")
    void handleValidationCase1() throws NoSuchMethodException {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(new Object(), "request");
        bindingResult.addError(new FieldError("request", "nome", "must not be blank"));

        Method method = FakeController.class.getDeclaredMethod("fakeMethod", String.class);
        MethodParameter parameter = new MethodParameter(method, 0);
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(parameter, bindingResult);

        ResponseEntity<Map<String, String>> response = apiExceptionHandler.handleValidation(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("must not be blank", response.getBody().get("nome"));
    }

    private static class FakeController {
        @SuppressWarnings("unused")
        public void fakeMethod(String request) {
        }
    }
}
