package it.biblioteca.project_work.utente_service.service;

import it.biblioteca.project_work.utente_service.dto.LoginResponseDto;
import it.biblioteca.project_work.utente_service.dto.UtenteDTO;

import java.util.List;

public interface UtenteService
{

    // Metodo per salvare l'utente passato come parametro
    UtenteDTO save(UtenteDTO utenteDTO);

    // Metodo per cercare
    UtenteDTO findByUuid(String uuid);
    List<UtenteDTO> findAll();
    void delete(String uuid);

   LoginResponseDto autenticaUtente(String username, String password);

}

// Questa interfaccia definisce le operazioni CRUD
//(Create, Read, Update, Delete) per gli utenti e un metodo di autenticazione: