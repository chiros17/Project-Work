package dto;

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
    private String bookUuid;
    private String userUuid;
    private String descrizione;
}
