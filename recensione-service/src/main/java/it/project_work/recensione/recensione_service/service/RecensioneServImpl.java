package it.project_work.recensione.recensione_service.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import it.project_work.recensione.recensione_service.dto.RecensioneDto;
import it.project_work.recensione.recensione_service.model.Recensione;
import it.project_work.recensione.recensione_service.repository.IRecensioneRepo;

@Service
@RequiredArgsConstructor
public class RecensioneServImpl implements IRecensioneService
{
    private final IRecensioneRepo recensioneRepo;

    @Override
    public RecensioneDto save(RecensioneDto recensioneDto)
    {
        if (recensioneDto.getUuid() == null || recensioneDto.getUuid().isEmpty()) // Controlla se l'UUID non è già stato impostato
        {
            recensioneDto.setUuid(UUID.randomUUID().toString()); // Genera UUID randomico e lo converte in Stringa
        }
        return modelToDTO(recensioneRepo.save(dtoToModel(recensioneDto)));
    }

    @Override
    public Double getAverageByBookUuid(String libroUuid)
    {
        return recensioneRepo.findByLibroUuid(libroUuid)
                .stream()
                .map(Recensione::getStars)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0);
    }

    @Override
    public List<RecensioneDto> getRecensioniByLibroUuid(String libroUuid)
    {
        return recensioneRepo.findByLibroUuid(libroUuid)
                .stream()
                .map(this::modelToDTO)
                .collect(Collectors.toList());
    }

    private Recensione dtoToModel(RecensioneDto recensioneDto)
    {
        return Recensione.builder()
                .libroUuid(recensioneDto.getLibroUuid())
                .utenteUuid(recensioneDto.getUtenteUuid())
                .contenuto(recensioneDto.getContenuto())
                .stars(recensioneDto.getStars())
                .build();

    }

    private RecensioneDto modelToDTO(Recensione model)
    {
        return RecensioneDto.builder()
            .contenuto(model.getContenuto())
            .stars(model.getStars())
            .libroUuid(model.getLibroUuid())
            .utenteUuid(model.getUtenteUuid())
            .dataRecensione(model.getDataRecensione())
            .build();
    }
}
