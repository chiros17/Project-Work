package it.project_work.recensione.recensione_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.project_work.recensione.recensione_service.model.Recensione;


@Repository
public interface IRecensioneRepo extends JpaRepository<Recensione, Long> {

    // Restituisce tutte le recensioni di un libro
    List<Recensione> findByLibroUuid(String libroUuid);


}
