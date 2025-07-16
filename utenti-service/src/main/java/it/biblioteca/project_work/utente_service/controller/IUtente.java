package it.biblioteca.project_work.utente_service.controller;

import it.biblioteca.project_work.utente_service.entity.Ruolo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class IUtente {
    public Long id;
    public String uuid;
    public String nome;
    public String email;
    public String username;
    public String password;
    public Ruolo ruolo;
}
