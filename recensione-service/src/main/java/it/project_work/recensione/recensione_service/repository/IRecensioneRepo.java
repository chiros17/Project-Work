package it.project_work.recensione.recensione_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.project_work.recensione.recensione_service.model.Recensione;


@Repository
public interface IRecensioneRepo extends JpaRepository<Recensione, Long>
{
    // Restituisce tutte le recensioni di un libro
    List<Recensione> findByLibroUuid(String libroUuid);

    // Metodo per trovare un determinato Prestito tramite l'uuid
    Optional<Recensione> findByUuid(String uuid );
}
