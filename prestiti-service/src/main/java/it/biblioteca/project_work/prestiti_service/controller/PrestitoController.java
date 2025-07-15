package it.biblioteca.project_work.prestiti_service.controller;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.biblioteca.project_work.prestiti_service.dto.PrestitoDto;
import it.biblioteca.project_work.prestiti_service.service.PrestitoServImpl;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/prestiti")
@RequiredArgsConstructor
public class PrestitoController
{
    private final PrestitoServImpl prestitoServImpl;

    @PostMapping
    public PrestitoDto save (@Valid @RequestBody PrestitoDto prestitoDto, @RequestParam String libroUuid, @RequestParam String utenteUuid)
    {
        return prestitoServImpl.save(prestitoDto, utenteUuid, libroUuid);
    }

    @PutMapping("/{prestitoUuid}/restituzione")
    public PrestitoDto restituisciLibro(@PathVariable String prestitoUuid)
    {
        return prestitoServImpl.restituisciLibro(prestitoUuid);
    }

    @GetMapping("/{prestitoUuid}")
    public PrestitoDto findByUuid(@PathVariable String prestitoUuid)
    {
        return prestitoServImpl.findByUuid(prestitoUuid);
    }

    @GetMapping
    public List <PrestitoDto> findAll()
    {
        return prestitoServImpl.findAll();
    }

    @GetMapping("/utente/{utenteUuid}/storico")
    public List<PrestitoDto> findByUtenteUuid(@PathVariable String utenteUuid)
    {
        return prestitoServImpl.findByUtenteUuid(utenteUuid);
    }

    @GetMapping("/libro/{bookUuid}/attivi")
    public List<PrestitoDto> trovaPrestitiAttivi(@PathVariable String bookUuid)
    {
        return prestitoServImpl.trovaPrestitiAttivi(bookUuid);
    }

}
