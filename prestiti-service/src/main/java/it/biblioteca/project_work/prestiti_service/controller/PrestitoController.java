package it.biblioteca.project_work.prestiti_service.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.biblioteca.project_work.prestiti_service.dto.PrestitoDto;
import it.biblioteca.project_work.prestiti_service.service.PrestitoServImpl;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/v1/prestiti")
@RequiredArgsConstructor
public class PrestitoController
{
    private final PrestitoServImpl prestitoServImpl;

    @PostMapping
    public PrestitoDto creaPrestito (@RequestBody PrestitoDto prestitoDto)
    {
        return prestitoServImpl.creaPrestito(prestitoDto);
    }

    @PutMapping("/{prestitoUuid}/restituzione")
    public PrestitoDto restituisciLibro(@PathVariable String prestitoUuid)
    {
        return prestitoServImpl.restituisciLibro(prestitoUuid);
    }

    @GetMapping("/{prestitoUuid}")
    public PrestitoDto trovaPrestito(@PathVariable String prestitoUuid)
    {
        return prestitoServImpl.trovaPrestito(prestitoUuid);
    }

    @GetMapping
    public List <PrestitoDto> showPrestiti()
    {
        return prestitoServImpl.listaTuttiIPrestiti();
    }

    @GetMapping("/utente/{utenteUuid}/storico")
     public List<PrestitoDto> storicoPrestitiUtente(@PathVariable String utenteUuid)
     {
         return prestitoServImpl.storicoPrestitiUtente(utenteUuid);
     }

    @GetMapping("/libro/{bookUuid}/attivi")
    public List<PrestitoDto> trovaPrestitiAttivi(@PathVariable String bookUuid)
    {
        return prestitoServImpl.trovaPrestitiAttivi(bookUuid);
    }

}
