package it.biblioteca.project_work.prestiti_service.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import it.biblioteca.project_work.prestiti_service.dto.PrestitoDto;
import it.biblioteca.project_work.prestiti_service.exception.PrestitoNotFoundException;
import it.biblioteca.project_work.prestiti_service.model.Prestito;
import it.biblioteca.project_work.prestiti_service.repository.PrestitoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PrestitoServImpl implements IPrestitoService
{
    private final PrestitoRepository prestitoRepository;

    @Override
    public PrestitoDto creaPrestito(PrestitoDto prestitoDto, String utenteUuid, String libroUuid)
    {

        if (prestitoDto.getUuid() == null || prestitoDto.getUuid().isEmpty()) // Controlla se l'UUID non è già stato impostato
        {
            prestitoDto.setUuid(UUID.randomUUID().toString()); // Genera UUID randomico e lo converte in Stringa
        }

        Prestito prestito = dtoToModel(prestitoDto);    // Converte il PrestitoDto in Prestito (model)

        prestito.setIsRestituito(false);  // ovviamente il prestito non è ancora restituito
        prestito.setDataRestituzione(null);     // inizialmente non c'è una data di restituzione

        prestito.setBookUuid(libroUuid);
        prestito.setUtenteUuid(utenteUuid);

        Prestito savedPrestito = prestitoRepository.save(prestito);     // Salva il Prestito nel database
        return modelToDto(savedPrestito);   // Converte il Prestito salvato in PrestitoDto e lo restituisce
    }

    @Override
    public PrestitoDto restituisciLibro(String prestitoUuid)
    {
        Prestito prestito = prestitoRepository.findByUuid(prestitoUuid)   // Trova il prestito per UUID
        
        // Se il prestito non esiste, lancia un'eccezione
                .orElseThrow(PrestitoNotFoundException::new);

        prestito.setIsRestituito(true);   // Imposta il prestito come restituito
        prestito.setDataRestituzione(LocalDate.now());  // Imposta la data di restituzione come la data corrente

        Prestito updatedPrestito = prestitoRepository.save(prestito);   // Salva le modifiche nel database
        return modelToDto(updatedPrestito);     // Converte il Prestito aggiornato in PrestitoDto
    }

    @Override
    public PrestitoDto trovaPrestito(String prestitoUuid)
    {
        return modelToDto(
            prestitoRepository.findByUuid(prestitoUuid)
            .orElseThrow(PrestitoNotFoundException::new)
        );
    }

    @Override
    public List<PrestitoDto> listaTuttiIPrestiti()
    {
        return prestitoRepository.findAll()
                .stream()
                .map(this::modelToDto) // Converte ogni Prestito in PrestitoDto
                .toList(); // Ritorna la lista di PrestitoDto
    }


    @Override
    public List<PrestitoDto> storicoPrestitiUtente(String uuidUtente)
    {
        return prestitoRepository.findByUtenteUuid(uuidUtente)
                .stream() // mette in fila tutti i prestiti dell'utente
                .map(this::modelToDto) // Converte ogni Prestito in PrestitoDto
                .toList(); // Ritorna la lista di PrestitoDto
    }
   
   
    @Override
    public List<PrestitoDto> trovaPrestitiAttivi(String uuidLibro)
    {
         return prestitoRepository.findByBookUuidAndIsRestituitoFalse(uuidLibro) 
         // Trova tutti i prestiti attivi (non ancora restituiti) per un dato libro
                .stream()       // mette in fila tutti i prestiti attivi
                .map(this::modelToDto)      // Converte ogni Prestito in PrestitoDto
                .toList();      // Ritorna la lista di PrestitoDto
    }

    //  Metodi di Conversione  

    // Converte  Prestito (model) in  PrestitoDto
    private PrestitoDto modelToDto(Prestito prestito)
    {
        return PrestitoDto.builder()
                .uuid(prestito.getUuid())
                .bookUuid(prestito.getBookUuid())
                .utenteUuid(prestito.getUtenteUuid())
                .dataInizioPrestito(prestito.getDataInizioPrestito())
                .dataRestituzione(prestito.getDataRestituzione()) 
                .isRestituito(prestito.getIsRestituito())
                .build();
    }

    // Converte  PrestitoDto in Prestito (model)
    private Prestito dtoToModel(PrestitoDto prestitoDto)
    {
        return Prestito.builder()
                .uuid(prestitoDto.getUuid())
                .bookUuid(prestitoDto.getBookUuid())
                .utenteUuid(prestitoDto.getUtenteUuid())
                .dataInizioPrestito(prestitoDto.getDataInizioPrestito())
                .dataRestituzione(prestitoDto.getDataRestituzione())
                .isRestituito(prestitoDto.getIsRestituito())
                .build();
    }
}
