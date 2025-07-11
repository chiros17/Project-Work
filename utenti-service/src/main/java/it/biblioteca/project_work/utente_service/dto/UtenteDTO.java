package it.biblioteca.project_work.utente_service.dto;

import it.biblioteca.project_work.utente_service.entity.Ruolo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UtenteDTO {
    private String uuid;
    private String nome;
    private String email;
    private Ruolo ruolo;

}
