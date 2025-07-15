package it.project_work.prenotazione.prenotazione_service.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrenotazioneDTO
{
    private String uuid;

    @NotNull
    @NotBlank
    private String libroUuid;

    @NotNull
    @NotBlank
    private String utenteUuid;

    @Size(min = 1, max = 100)
    private String descrizione;
}
