package it.biblioteca.project_work.utente_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.biblioteca.project_work.utente_service.dto.LoginRequestDto;
import it.biblioteca.project_work.utente_service.dto.LoginResponseDto;
import it.biblioteca.project_work.utente_service.dto.UtenteDTO;
import it.biblioteca.project_work.utente_service.service.UtenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RestController     //Identifica questa classe come controller
@RequestMapping("/api/v1/utenti")   //Questa annotazione, applicata a livello di classe, definisce il percorso base  per tutte le operazioni gestite da questo controller.
@RequiredArgsConstructor    // Genera un costruttore che inietta UtenteService
public class UtenteController
{

    private final UtenteService utenteService; //dipendenza da service

    //Prende l'intero oggetto  inviato nel corpo del messaggio HTTP
    @PostMapping
    public UtenteDTO save( @Valid @RequestBody UtenteDTO utenteDTO ) { return utenteService.save(utenteDTO); }

    //Estrae il valore dell'UUID dall'URL e lo mappa alla variabile uuid del metodo.
    @GetMapping("/{uuid}")
    public UtenteDTO findByUuid( @PathVariable String uuid ) { return utenteService.findByUuid(uuid); }

    @GetMapping
    public List<UtenteDTO> findAll() { return utenteService.findAll(); }

    // Estrae il valore dell'UUID dall'URL e lo mappa alla variabile uuid del metodo.
    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable String uuid) { utenteService.delete(uuid); }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> autenticaUtente(@RequestBody LoginRequestDto loginRequest)
    {
        LoginResponseDto dtoResponse = utenteService.autenticaUtente(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(dtoResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() { return ResponseEntity.ok("Logout effettuato con successo. ."); }

}

// Il controller ha la sola responsabilità di ricevere le richieste dal client ,Get ,Post ecc... e restituire i valori chiesti
// Ha bisogno del service poichè comunica a lui di elaborare le richieste
