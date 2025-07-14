package it.project_work.recensione.recensione_service.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RecensioneDto {


    private String uuid;
    private String contenuto;
    private double stars;
    private String libroUuid;
    private String utenteUuid;
    private String dataRecensione;
    


}
