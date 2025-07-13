package it.biblioteca.project_work.prestiti_service.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Prestito {

    @Id
 
    private String uuid; 
    
    private String bookUuid;

    private String utenteUuid;
    
    private LocalDate dataInizioPrestito;
    
    private LocalDate dataRestituzione;
    
    private boolean isRestituito;


}
