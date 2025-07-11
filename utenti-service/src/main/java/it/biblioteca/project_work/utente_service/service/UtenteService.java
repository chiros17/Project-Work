package it.biblioteca.project_work.utente_service.service;

import it.biblioteca.project_work.utente_service.dto.UtenteDTO;

import java.util.List;

public interface UtenteService {

    UtenteDTO creaUtente(UtenteDTO utenteDTO);

    UtenteDTO trovaUtente(String uuid);
    List<UtenteDTO> listaUtenti();
    void eliminaUtente(String uuid);

    UtenteDTO autenticaUtente(String username, String password);

}
