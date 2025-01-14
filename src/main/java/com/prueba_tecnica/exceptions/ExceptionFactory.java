package com.prueba_tecnica.exceptions;


import com.prueba_tecnica.exceptions.custom.CustomBaseException;
import java.lang.reflect.Constructor;

public class ExceptionFactory{
    public static <T extends CustomBaseException> T createException(Class<T> exceptionClass, String message){
        try{
            Constructor<T> constructor = exceptionClass.getDeclaredConstructor(String.class);
            return constructor.newInstance(message);
        }catch (Exception e){
            throw new RuntimeException("Error creando la instancia de la excepcion. ", e);
        }
    }

    public static <T extends CustomBaseException> T createException(Class<T> exceptionClass){
        try{
            Constructor<T> constructor = exceptionClass.getDeclaredConstructor();
            return constructor.newInstance();
        }catch (Exception e){
            throw new RuntimeException("Error creando la instancia de la excepcion. ", e);
        }    }


}

