package it.biblioteca.project_work.utente_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequestDto {

  @NotNull(message = " Il campo username non può essere nullo.")
  @NotBlank(message = " Devi inserire un username.")
    private String username;
    @NotNull(message = "Il campo password non può essere nullo")
    @NotBlank(message = "Devi inserire una password.")
    private String password;
 // Questa classe rappresenta la richiesta di login e contiene i campi username e password. 
}
