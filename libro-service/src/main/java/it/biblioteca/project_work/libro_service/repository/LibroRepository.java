package it.biblioteca.project_work.libro_service.repository;

import it.biblioteca.project_work.libro_service.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long>
{
    // Metodo per ottenere un determinato libro tramite l'uuid
    Optional<Libro> findByUuid( String uuid );
}
