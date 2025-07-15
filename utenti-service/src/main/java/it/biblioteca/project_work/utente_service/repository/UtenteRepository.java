package it.biblioteca.project_work.utente_service.repository;

import it.biblioteca.project_work.utente_service.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface UtenteRepository extends JpaRepository<Utente, Long>
{
    // Metodo per la ricerca di un utente tramite username
    Optional<Utente> findByUsername(String username);

    // Metodo di ricerca di un utente tramite uuid
    Optional<Utente> findByUuid(String uuid);
}
