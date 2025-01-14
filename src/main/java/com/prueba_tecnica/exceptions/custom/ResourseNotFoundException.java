package com.prueba_tecnica.exceptions.custom;
import org.springframework.http.HttpStatus;

public class ResourseNotFoundException extends CustomBaseException {
    private static final String DEFAULT_MESSAGE = "Equipo no encontrado";
    private static final Integer DEFAULT_KEY_CODE = HttpStatus.NOT_FOUND.value();

    public ResourseNotFoundException(String mensaje) {
        super(mensaje, DEFAULT_KEY_CODE, DEFAULT_MESSAGE);
    }

    public ResourseNotFoundException(String mensaje, Integer codigo) {
        super(null, codigo, DEFAULT_MESSAGE);
    }

}
