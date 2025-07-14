package it.biblioteca.project_work.utente_service.controller;

import it.biblioteca.project_work.utente_service.dto.UtenteDTO;
import it.biblioteca.project_work.utente_service.service.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController //Identifica questa classe come controller
@RequestMapping("/api/v1/utenti") //Questa annotazione, applicata a livello di classe, definisce il percorso base  per tutte le operazioni gestite da questo controller.
@RequiredArgsConstructor // Genera un costruttore che inietta UtenteService
public class UtenteController {

    private final UtenteService utenteService; //dipendenza da service

    @PostMapping
    public UtenteDTO creaUtente( @Valid @RequestBody UtenteDTO utenteDTO){//Prende l'intero oggetto  inviato nel corpo del messaggio HTTP 
        return utenteService.creaUtente(utenteDTO);
    }

    @GetMapping("/{uuid}")
    public UtenteDTO trovaUtente(@PathVariable String uuid) { //Estrae il valore dell'UUID dall'URL e lo mappa alla variabile uuid del metodo.
        return utenteService.trovaUtente(uuid);
    }

    @GetMapping
    public List<UtenteDTO> listaUtenti(){
        return utenteService.listaUtenti();
    }
    @DeleteMapping("/{uuid}")
    public void eliminaUtente(@PathVariable String uuid) {// Estrae il valore dell'UUID dall'URL e lo mappa alla variabile uuid del metodo.
        utenteService.eliminaUtente(uuid);
    }




}
//   Il controller ha la sola responsabilità di ricevere le richieste dal client ,Get ,Post ecc... e restituire i valori chiesti
// Ha bisogno del service poichè comunica a lui di elaborare le richieste
