package it.project_work.recensione.recensione_service.controller;

import it.project_work.recensione.recensione_service.dto.RecensioneDto;
import it.project_work.recensione.recensione_service.service.IRecensioneService;

import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recensioni")
public class RecensioneController
{

    private final IRecensioneService recensioneService;
    
    @PostMapping
    public RecensioneDto save(@Valid @RequestBody RecensioneDto recensioneDto, @RequestParam String utenteUuid, @RequestParam String libroUuid)
    { return recensioneService.save(recensioneDto, utenteUuid, libroUuid); }

    @GetMapping
    public Double average(@RequestParam String libroUuid) { return recensioneService.getAverageByBookUuid(libroUuid); }

    @GetMapping("/lista")
    public List<RecensioneDto> getRecensioni(@RequestParam String libroUuid) { return recensioneService.getRecensioniByLibroUuid(libroUuid); }
}
