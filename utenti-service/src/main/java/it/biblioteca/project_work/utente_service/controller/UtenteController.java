package it.biblioteca.project_work.utente_service.controller;

import it.biblioteca.project_work.utente_service.dto.UtenteDTO;
import it.biblioteca.project_work.utente_service.service.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin( origins = "localhost://4200")
@RestController
@RequestMapping("/api/utenti")
@RequiredArgsConstructor
public class UtenteController {

    private final UtenteService utenteService;

    @PostMapping
    public UtenteDTO creaUtente(@RequestBody UtenteDTO utenteDTO){
        return utenteService.creaUtente(utenteDTO);
    }

    @GetMapping("/{uuid}")
    public UtenteDTO trovaUtente(@PathVariable String uuid) {
        return utenteService.trovaUtente(uuid);
    }

    @GetMapping
    public List<UtenteDTO> listaUtenti(){
        return utenteService.listaUtenti();
    }
    @DeleteMapping("/{uuid}")
    public void eliminaUtente(@PathVariable String uuid) {
        utenteService.eliminaUtente(uuid);
    }




}
