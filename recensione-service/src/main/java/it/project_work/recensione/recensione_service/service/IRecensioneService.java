package it.project_work.recensione.recensione_service.service;

import java.util.List;

import it.project_work.recensione.recensione_service.dto.RecensioneDto;

public interface IRecensioneService {

    RecensioneDto save( RecensioneDto recensioneDto );
    Double getAverageByBookUuid( String libroUuid );
    List<RecensioneDto> getRecensioniByLibroUuid(String libroUuid);


}
