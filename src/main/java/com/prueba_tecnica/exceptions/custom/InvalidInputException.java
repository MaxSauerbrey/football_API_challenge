package com.prueba_tecnica.exceptions.custom;

import org.springframework.http.HttpStatus;

public class InvalidInputException extends CustomBaseException {
    private static final String DEFAULT_MESSAGE = "La solicitud es invalida";
    private static final Integer DEFAULT_KEY_CODE = HttpStatus.BAD_REQUEST.value();

    public InvalidInputException(String mensaje) {
        super(mensaje, DEFAULT_KEY_CODE, DEFAULT_MESSAGE);
    }

    public InvalidInputException(String mensaje, Integer codigo){
        super(null, codigo, DEFAULT_MESSAGE);
    }
}
