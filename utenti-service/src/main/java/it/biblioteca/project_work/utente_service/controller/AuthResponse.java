package it.biblioteca.project_work.utente_service.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.biblioteca.project_work.utente_service.dto.UtenteDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AuthResponse
{

    public String token;

    @JsonProperty("user")
    public UtenteDTO utente;

}

