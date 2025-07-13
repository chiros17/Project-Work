package it.biblioteca.project_work.prestiti_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.biblioteca.project_work.prestiti_service.model.Prestito;

public interface PrestitoRepository  extends JpaRepository<Prestito,String> {

     
    List<Prestito> findByUtenteUuid(String utenteUuid);// Metodo per trovare tutti i prestiti associati a un utente specifico

    
    List<Prestito> findByBookUuidAndIsRestituitoFalse(String bookUuid);
// Metodo per trovare tutti i prestiti attivi (non ancora restituiti) per un dato libro

    

}
