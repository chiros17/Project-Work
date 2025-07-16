package it.biblioteca.project_work.utente_service.controller;

import it.biblioteca.project_work.utente_service.dto.UtenteDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AuthResponse
{

    public UtenteDTO utente;
    public String token;

}

