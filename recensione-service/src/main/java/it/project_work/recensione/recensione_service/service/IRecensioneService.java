package it.project_work.recensione.recensione_service.service;

import java.util.List;

import it.project_work.recensione.recensione_service.dto.RecensioneDto;

public interface IRecensioneService
{

    RecensioneDto save( RecensioneDto recensioneDto );  // Metodo per salvare le recensioni
    Double getAverageByBookUuid( String libroUuid );    //Metodo per ottenere la media delle recensioni di un determinato libro
    List<RecensioneDto> getRecensioniByLibroUuid(String libroUuid); // Metodo per ottenere le recensioni di un determinato libro

}
