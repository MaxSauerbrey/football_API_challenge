package com.prueba_tecnica.exceptions.custom;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public abstract class CustomBaseException extends RuntimeException{
    private String mensaje;
    private Integer codigo;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    protected CustomBaseException(String message){
        super(message);
    }

    protected CustomBaseException(String message, Integer codigo, String description){
        super(message != null ? message : description);
        this.codigo = codigo;
        this.mensaje = description;
    }

    public CustomBaseException(String message, String defaultMessage) {
        super(message != null ? message : defaultMessage);
        this.mensaje = defaultMessage;
    }

}



