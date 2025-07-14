package it.biblioteca.project_work.prestiti_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.biblioteca.project_work.prestiti_service.model.Prestito;

public interface PrestitoRepository extends JpaRepository<Prestito,Long>
{

    // Metodo per trovare tutti i prestiti associati a un utente specifico
    List<Prestito> findByUtenteUuid(String utenteUuid);

    // Metodo per trovare tutti i prestiti attivi (non ancora restituiti) per un dato libro
    List<Prestito> findByBookUuidAndIsRestituitoFalse(String bookUuid);

    // Metodo per trovare un determinato Prestito tramite l'uuid
    Optional<Prestito> findByUuid( String uuid );
}
