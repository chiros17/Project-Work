package service;

import dto.LibroDTO;

import java.util.List;

public interface LibroService
{
    // Metodo per avere la lista di tutti i libri
    List<LibroDTO> findAll();

    // Ricerca per uuid passato come parametro
    LibroDTO findByUuid( String uuid );

    // Salva un nuovo librodto nel database
    LibroDTO save( LibroDTO book );

    // Aggiornamento totale del entità passata
    LibroDTO update(String uuid, LibroDTO book );

    // Aggiornamento parziale del libro passato
    LibroDTO partialUpdate(String uuid, LibroDTO book );

    // Eliminazione di un libro tramite l'uuid
    void deleteByUuid( String uuid );
}
