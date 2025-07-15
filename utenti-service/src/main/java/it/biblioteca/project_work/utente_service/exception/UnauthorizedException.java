package it.biblioteca.project_work.utente_service.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super("Credenziali non valide.");
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}

// Questa classe rappresenta un'eccezione di autorizzazione non valida
// e viene lanciata quando le credenziali fornite non sono valide.