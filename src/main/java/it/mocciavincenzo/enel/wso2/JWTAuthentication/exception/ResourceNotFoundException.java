package it.mocciavincenzo.enel.wso2.JWTAuthentication.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException() {
        super("Risorsa non trovata");
    }
}
