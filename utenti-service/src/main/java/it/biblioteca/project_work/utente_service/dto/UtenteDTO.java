package it.biblioteca.project_work.utente_service.dto;

import it.biblioteca.project_work.utente_service.entity.Ruolo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data   // annotazione  Lombok che genera automaticamente  il codice boilerplate (usata per i DTO)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UtenteDTO
{
    private String uuid; 


    @NotNull
    private String nome;

    @NotBlank(message = "L'email dell'utente non può essere vuota.")
    @Email(message = "Il formato dell'email non è valido.")
    @Size(max = 100, message = "L'email non può superare i 100 caratteri.")
    private String email;

    @NotBlank(message = "L'username non può essere vuoto.")
    private String username;

    @NotBlank(message = "La password non può essere vuota.")
    @Size(min = 6, message = "La password deve avere almeno 6 caratteri.")
    private String password;

    @NotNull
    private Ruolo ruolo;
}

// Questa classe contiene i dati essenziali ed è usata per scambiare informazioni tra i layer
// Ha la responsabilità di evitare i dati sensibili quindi grazie ad essa non si espone direttamente l'entità del database