package service;

import dto.PrenotazioneDTO;

import java.util.List;

public interface PrenotazioneService
{
    List<PrenotazioneDTO> findAll();
    PrenotazioneDTO findByUuid( String uuid );
    PrenotazioneDTO save( PrenotazioneDTO prenotazione );
    PrenotazioneDTO update(String uuid, PrenotazioneDTO prenotazione );
}
