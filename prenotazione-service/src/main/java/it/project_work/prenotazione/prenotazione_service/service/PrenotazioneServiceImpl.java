package it.project_work.prenotazione.prenotazione_service.service;

import it.project_work.prenotazione.prenotazione_service.dto.PrenotazioneDTO;
import it.project_work.prenotazione.prenotazione_service.exception.PrenotazioneNotFoundException;
import it.project_work.prenotazione.prenotazione_service.model.Prenotazione;
import it.project_work.prenotazione.prenotazione_service.repository.PrenotazioneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrenotazioneServiceImpl implements PrenotazioneService
{
    private final PrenotazioneRepository prenotazioneRepository;

    @Override
    public List<PrenotazioneDTO> findAll()
    {
        return prenotazioneRepository.findAll()
                .stream()
                .map(this::modelToDTO)
                .toList();
    }

    @Override
    public PrenotazioneDTO findByUuid(String uuid)
    {
        return modelToDTO(prenotazioneRepository.findByUuid(uuid).orElseThrow(PrenotazioneNotFoundException::new));
    }

    @Override
    public PrenotazioneDTO save(PrenotazioneDTO prenotazioneDTO)
    {
        if (prenotazioneDTO.getUuid() == null || prenotazioneDTO.getUuid().isEmpty()) // Controlla se l'UUID non è già stato impostato
        {
            prenotazioneDTO.setUuid(UUID.randomUUID().toString()); // Genera UUID randomico e lo converte in Stringa
        }
        return modelToDTO(prenotazioneRepository.save(dtoToModel(prenotazioneDTO)));
    }

    @Override
    public PrenotazioneDTO update(String uuid, PrenotazioneDTO prenotazione)
    {
        PrenotazioneDTO prenotazioneDTO = modelToDTO(prenotazioneRepository.findByUuid(uuid).orElseThrow(PrenotazioneNotFoundException:: new));

        prenotazioneDTO.setDescrizione(prenotazione.getDescrizione());
        return prenotazioneDTO;
    }

    private Prenotazione dtoToModel(PrenotazioneDTO prenotazioneDTO)
    {
        return Prenotazione.builder()
                .uuid(prenotazioneDTO.getUuid())
                .libroUuid(prenotazioneDTO.getLibroUuid())
                .utenteUuid(prenotazioneDTO.getUtenteUuid())
                .descrizione(prenotazioneDTO.getDescrizione())
                .build();
    }

    private PrenotazioneDTO modelToDTO(Prenotazione prenotazione)
    {
        return PrenotazioneDTO.builder()
                .uuid(prenotazione.getUuid())
                .libroUuid(prenotazione.getLibroUuid())
                .utenteUuid(prenotazione.getUtenteUuid())
                .descrizione(prenotazione.getDescrizione())
                .build();
    }
}
