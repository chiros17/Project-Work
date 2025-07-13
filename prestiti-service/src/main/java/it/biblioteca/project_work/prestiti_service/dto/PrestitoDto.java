package it.biblioteca.project_work.prestiti_service.dto;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrestitoDto {


    
    
    private String uuid;

    private String bookUuid;

    private String utenteUuid;

    private LocalDate dataInizioPrestito;

    
    private LocalDate dataRestituzione;


    private boolean isRestituito;


}
