package it.project_work.recensione.recensione_service.controller;

import it.project_work.recensione.recensione_service.dto.RecensioneDto;
import it.project_work.recensione.recensione_service.service.IRecensioneService;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recensioni")
public class RecensioneController
{

    private final IRecensioneService recensioneService;
    
    @PostMapping
    public RecensioneDto save(@RequestBody  RecensioneDto recensioneDto)
    {
        return recensioneService.save(recensioneDto);
    }

    @GetMapping
    public Double average(@RequestParam String libroUuid)
    {
        return recensioneService.getAverageByBookUuid(libroUuid);
    }

    @GetMapping("/lista")
    public List<RecensioneDto> getRecensioni(@RequestParam String libroUuid)
    {
        return recensioneService.getRecensioniByLibroUuid(libroUuid);
    }
}
