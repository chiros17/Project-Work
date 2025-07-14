package it.project_work.recensione.recensione_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecensioneDto
{

    private String uuid;
    private String contenuto;
    private double stars;
    private String libroUuid;
    private String utenteUuid;
    private String dataRecensione;

}
