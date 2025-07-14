package it.biblioteca.project_work.prestiti_service.service;

import java.util.List;

import it.biblioteca.project_work.prestiti_service.dto.PrestitoDto;

public interface IPrestitoService
{
    PrestitoDto creaPrestito(PrestitoDto prestitoDto);      // Corrisponde a 'save'
    PrestitoDto restituisciLibro(String prestitoUuid);      // Metodo specifico per restituire un libro
    PrestitoDto trovaPrestito(String prestitoUuid);     // Corrisponde a 'findByUuid'
    List<PrestitoDto> listaTuttiIPrestiti();    // Corrisponde a 'findAll'
    List<PrestitoDto> storicoPrestitiUtente(String uuidUtente);
    List<PrestitoDto> trovaPrestitiAttivi(String uuidLibro);
}
