package it.project_work.prenotazione.prenotazione_service.repository;

import it.project_work.prenotazione.prenotazione_service.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long>
{
    Optional<Prenotazione> findByUuid( String uuid );
}
