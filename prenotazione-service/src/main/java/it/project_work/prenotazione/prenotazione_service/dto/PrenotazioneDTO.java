package it.project_work.prenotazione.prenotazione_service.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrenotazioneDTO
{
    private String uuid;
    private String libroUuid;
    private String utenteUuid;
    private String descrizione;
}
