package it.biblioteca.project_work.utente_service.service;

import java.util.List;

import it.biblioteca.project_work.utente_service.controller.AuthResponse;
import it.biblioteca.project_work.utente_service.dto.UtenteDTO;

public interface UtenteService
{

    // Metodo per salvare l'utente passato come parametro
    UtenteDTO save(UtenteDTO utenteDTO);

    // Metodo per cercare
    UtenteDTO findByUuid(String uuid);
    List<UtenteDTO> findAll();
    void delete(String uuid);

   AuthResponse autenticaUtente(String username, String password);

}

// Questa interfaccia definisce le operazioni CRUD
//(Create, Read, Update, Delete) per gli utenti e un metodo di autenticazione: