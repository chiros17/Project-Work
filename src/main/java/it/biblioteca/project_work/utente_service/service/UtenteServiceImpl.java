package it.biblioteca.project_work.utente_service.service;
import it.biblioteca.project_work.utente_service.entity.Utente;
import it.biblioteca.project_work.utente_service.dto.UtenteDTO;
import it.biblioteca.project_work.utente_service.exception.UserNotFoundException;
import it.biblioteca.project_work.utente_service.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UtenteServiceImpl implements UtenteService{
    // Iniezione del repository

    private final UtenteRepository utenteRepository;


    @Override
    public UtenteDTO creaUtente(UtenteDTO utenteDTO) {
        return modelToDto(utenteRepository.save(dtoToModel(utenteDTO)));
    }

    @Override
    public UtenteDTO trovaUtente(String uuid) {
        // Logica per trovare un utente per ID
        return modelToDto(utenteRepository.findByUuid(uuid).orElseThrow(UserNotFoundException::new)); // Placeholder
    }

    @Override
    public List<UtenteDTO> listaUtenti() {
        // Logica per elencare tutti gli utenti
        return utenteRepository.findAll().stream().map(this::modelToDto).toList();
    }

    @Override
    public void eliminaUtente(String uuid) {
        // Logica per eliminare un utente
        Utente utente = utenteRepository.findByUuid(uuid).orElseThrow(UserNotFoundException::new);
        utenteRepository.deleteById(utente.getId());
    }

    @Override
    public UtenteDTO autenticaUtente(String username, String password) {
        // Logica per autenticare un utente
        return UtenteDTO.builder().build(); // Placeholder
    }

    private UtenteDTO modelToDto( Utente utente ){
        return UtenteDTO.builder()
                .uuid(utente.getUuid())
                .nome(utente.getNome())
                .email(utente.getEmail())
                .ruolo(utente.getRuolo())
                .build();
    }

    private Utente dtoToModel( UtenteDTO utente ){
        return Utente.builder()
                .uuid(utente.getUuid())
                .nome(utente.getNome())
                .email(utente.getEmail())
                .ruolo(utente.getRuolo())
                .build();
    }
}
