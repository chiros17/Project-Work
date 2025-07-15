package it.biblioteca.project_work.utente_service.service;
import it.biblioteca.project_work.utente_service.entity.Utente;
import it.biblioteca.project_work.utente_service.dto.LoginResponseDto;
import it.biblioteca.project_work.utente_service.dto.UtenteDTO;
import it.biblioteca.project_work.utente_service.exception.UnauthorizedException;
import it.biblioteca.project_work.utente_service.exception.UserNotFoundException;
import it.biblioteca.project_work.utente_service.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UtenteServiceImpl implements UtenteService
{

    // Iniezione del repository

    private final UtenteRepository utenteRepository;

    @Override
    public UtenteDTO save(UtenteDTO utenteDTO)
    {
        if (utenteDTO.getUuid() == null || utenteDTO.getUuid().isEmpty()) // Controlla se l'UUID non è già stato impostato
        {
            utenteDTO.setUuid(UUID.randomUUID().toString()); // Genera UUID randomico e lo converte in Stringa
        }

        Utente utenteToSave = dtoToModel(utenteDTO); // Converte il DTO in Model
        utenteToSave.setUsername(utenteDTO.getUsername());// Imposta il nome utente
        utenteToSave.setPassword(utenteDTO.getPassword());// Imposta la password
        return modelToDto(utenteRepository.save(dtoToModel(utenteDTO)));
    }

    @Override
    public UtenteDTO findByUuid(String uuid)
    {
        // Logica per trovare un utente per ID
        return modelToDto(utenteRepository.findByUuid(uuid).orElseThrow(UserNotFoundException::new)); // se non trovato lanciamo l'exception personalizzata
    }

    @Override
    public List<UtenteDTO> listaUtenti()
    {
        // Logica per elencare tutti gli utenti
        return utenteRepository.findAll().stream().map(this::modelToDto).toList();
    }

    @Override
    public void delete(String uuid)
    {
        // Logica per eliminare un utente
        Utente utente = utenteRepository.findByUuid(uuid).orElseThrow(UserNotFoundException::new);
        utenteRepository.deleteById(utente.getId());
    }

    @Override
    public LoginResponseDto autenticaUtente(String username, String password)
    {
        Optional<Utente> userOptional = utenteRepository.findByUsername(username);

        if (userOptional.isPresent())
        {
            Utente utente = userOptional.get();

            if (utente.getPassword().equals(password))
            {
                return LoginResponseDto.builder()
                        .uuid(utente.getUuid())
                        .nome(utente.getNome())
                        .email(utente.getEmail())
                        .username(utente.getUsername())
                        .ruolo(utente.getRuolo())
                        .build();
            }
        }

        // Se l'utente non è presente o la password non corrisponde, lancia l'eccezione
        throw new UnauthorizedException("Username o password non validi.");
    }

    // Conversione da model a Dto

    private UtenteDTO modelToDto ( Utente utente )
    {
        return UtenteDTO.builder()
                .uuid(utente.getUuid())
                .nome(utente.getNome())
                .email(utente.getEmail())
                .username(utente.getUsername())
                .ruolo(utente.getRuolo())
                .build();
    }

    // Conversione da Dto a model

    private Utente dtoToModel( UtenteDTO utente )
    {
        return Utente.builder()
                .uuid(utente.getUuid())
                .nome(utente.getNome())
                .email(utente.getEmail())
                .username(utente.getUsername())
                .password(utente.getPassword())
                .ruolo(utente.getRuolo())
                .build();
    }
}

//Questa classe implementa l'interfaccia UtenteService 
//e gestisce l'interazione con il UtenteRepository.
// Include anche metodi per la conversione tra DTO (Data Transfer Object) e Model (Entity). 
//Le operazioni di trovaUtente ed eliminaUtente 
//gestiscono l'eccezione UserNotFoundException se un utente non viene trovato.