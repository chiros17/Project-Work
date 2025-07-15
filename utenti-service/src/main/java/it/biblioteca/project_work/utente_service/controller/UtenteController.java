package it.biblioteca.project_work.utente_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.biblioteca.project_work.utente_service.dto.UtenteDTO;
import it.biblioteca.project_work.utente_service.service.UtenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController     //Identifica questa classe come controller
@RequestMapping("/api/v1/utenti")   //Questa annotazione, applicata a livello di classe, definisce il percorso base  per tutte le operazioni gestite da questo controller.
@RequiredArgsConstructor    // Genera un costruttore che inietta UtenteService
public class UtenteController
{

    private final UtenteService utenteService; //dipendenza da service

    //Prende l'intero oggetto  inviato nel corpo del messaggio HTTP
    @PostMapping
    public UtenteDTO creaUtente( @Valid @RequestBody UtenteDTO utenteDTO) { return utenteService.creaUtente(utenteDTO); }

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



    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> autenticaUtente(@RequestBody LoginRequestDto loginRequest) {
        LoginResponseDto dtoResponse= utenteService.autenticaUtente(loginRequest.getUsername(), loginRequest.getPassword());
return ResponseEntity.ok(dtoResponse);
    }





    @PostMapping("/logout")
    public ResponseEntity<String> logout() {


        return ResponseEntity.ok("Logout effettuato con successo. .");
    }

}

// Il controller ha la sola responsabilità di ricevere le richieste dal client ,Get ,Post ecc... e restituire i valori chiesti
// Ha bisogno del service poichè comunica a lui di elaborare le richieste
