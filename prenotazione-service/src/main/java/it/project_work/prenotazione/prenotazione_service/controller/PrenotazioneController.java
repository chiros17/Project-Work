package it.project_work.prenotazione.prenotazione_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.project_work.prenotazione.prenotazione_service.dto.PrenotazioneDTO;
import it.project_work.prenotazione.prenotazione_service.service.PrenotazioneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/prenotazioni")
public class PrenotazioneController
{
    private final PrenotazioneService prenotazioneService;

    @GetMapping
    public List<PrenotazioneDTO> findAll()
    {
        return prenotazioneService.findAll();
    }

    @PutMapping
    public PrenotazioneDTO save(@Valid @RequestBody PrenotazioneDTO prenotazioneDTO, @RequestParam String utenteUuid, @RequestParam String libroUuid)
    { return prenotazioneService.save(prenotazioneDTO, utenteUuid, libroUuid); }

    @PatchMapping
    public PrenotazioneDTO update(@RequestBody String uuid, @RequestBody PrenotazioneDTO prenotazioneDTO) { return prenotazioneService.update(uuid, prenotazioneDTO); }

    @GetMapping("/{uuid}")
    public PrenotazioneDTO findByUuid(@PathVariable String uuid)
    {
        return prenotazioneService.findByUuid(uuid);
    }
}
