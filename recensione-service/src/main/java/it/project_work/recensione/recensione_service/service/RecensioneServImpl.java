package it.project_work.recensione.recensione_service.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
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
        Recensione recensione = dtoToModel(recensioneDto);
        recensione = recensioneRepo.save(recensione);
        return modelToDTO(recensione);
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



    // Metodi di conversione
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;


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
        String dataString = null;
        if (model.getDataRecensione() != null)
        {
            dataString = model.getDataRecensione().format(formatter);
        }
        return RecensioneDto.builder()
            .contenuto(model.getContenuto())
            .stars(model.getStars())
            .libroUuid(model.getLibroUuid())
            .utenteUuid(model.getUtenteUuid())
            .dataRecensione(dataString)
            .build();
    }
}
