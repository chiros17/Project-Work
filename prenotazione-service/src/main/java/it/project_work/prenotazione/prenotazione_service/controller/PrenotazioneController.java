package it.project_work.prenotazione.prenotazione_service.controller;

import it.project_work.prenotazione.prenotazione_service.dto.PrenotazioneDTO;
import it.project_work.prenotazione.prenotazione_service.service.PrenotazioneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public PrenotazioneDTO save(PrenotazioneDTO prenotazioneDTO)
    {
        return prenotazioneService.save(prenotazioneDTO);
    }

    @PatchMapping
    public PrenotazioneDTO update(@RequestBody String uuid, @RequestBody PrenotazioneDTO prenotazioneDTO)
    {
        return prenotazioneService.update(uuid, prenotazioneDTO);
    }

    @GetMapping("/{uuid}")
    public PrenotazioneDTO findByUuid(@PathVariable String uuid)
    {
        return prenotazioneService.findByUuid(uuid);
    }
}
