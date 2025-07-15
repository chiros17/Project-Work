package it.biblioteca.project_work.prestiti_service.service;

import java.util.List;

import it.biblioteca.project_work.prestiti_service.dto.PrestitoDto;

public interface IPrestitoService
{
    PrestitoDto save(PrestitoDto prestitoDto, String utenteUuid, String libroUuid);      // Corrisponde a 'save'
    PrestitoDto restituisciLibro(String prestitoUuid);      // Metodo specifico per restituire un libro
    PrestitoDto findByUuid(String prestitoUuid);     // Corrisponde a 'findByUuid'
    List<PrestitoDto> findAll();    // Corrisponde a 'findAll'
    List<PrestitoDto> findByUtenteUuid(String uuidUtente);
    List<PrestitoDto> trovaPrestitiAttivi(String uuidLibro);
}
