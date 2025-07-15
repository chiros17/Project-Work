package it.project_work.prenotazione.prenotazione_service.service;


import it.project_work.prenotazione.prenotazione_service.dto.PrenotazioneDTO;

import java.util.List;

public interface PrenotazioneService
{
    List<PrenotazioneDTO> findAll();
    PrenotazioneDTO findByUuid( String uuid );
    PrenotazioneDTO save( PrenotazioneDTO prenotazione, String utenteUuid, String libroUuid );
    PrenotazioneDTO update(String uuid, PrenotazioneDTO prenotazione );
}
