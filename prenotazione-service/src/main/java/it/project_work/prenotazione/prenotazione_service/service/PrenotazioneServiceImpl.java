package service;

import dto.PrenotazioneDTO;
import exception.PrenotazioneNotFoundException;
import lombok.RequiredArgsConstructor;
import model.Prenotazione;
import org.springframework.stereotype.Service;
import repository.PrenotazioneRepository;

import java.util.List;

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
        return modelToDTO(prenotazioneRepository.save(dtoToModel(prenotazioneDTO)));
    }

    @Override
    public PrenotazioneDTO update(String uuid, PrenotazioneDTO prenotazione)
    {
        PrenotazioneDTO prenotazioneDTO = modelToDTO(prenotazioneRepository.findByUuid(uuid).orElseThrow(PrenotazioneNotFoundException :: new));

        prenotazioneDTO.setDescrizione(prenotazione.getDescrizione());
        return prenotazioneDTO;
    }

    private Prenotazione dtoToModel(PrenotazioneDTO prenotazioneDTO)
    {
        return Prenotazione.builder()
                .uuid(prenotazioneDTO.getUuid())
                .bookUuid(prenotazioneDTO.getBookUuid())
                .userUuid(prenotazioneDTO.getUserUuid())
                .descrizione(prenotazioneDTO.getDescrizione())
                .build();
    }

    private PrenotazioneDTO modelToDTO(Prenotazione prenotazione)
    {
        return PrenotazioneDTO.builder()
                .uuid(prenotazione.getUuid())
                .bookUuid(prenotazione.getBookUuid())
                .userUuid(prenotazione.getUserUuid())
                .descrizione(prenotazione.getDescrizione())
                .build();
    }
}
