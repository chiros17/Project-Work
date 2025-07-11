package it.biblioteca.project_work.utente_service.repository;

import it.biblioteca.project_work.utente_service.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Long> {

    Optional<Utente> findByUsername(String username);
    Optional<Utente> findByUuid(String uuid);
}
