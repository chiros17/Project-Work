package it.biblioteca.project_work.libro_service.controller;

import it.biblioteca.project_work.libro_service.dto.LibroDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import it.biblioteca.project_work.libro_service.service.LibroService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/libri")
public class LibroController
{
    private final LibroService bookService;

    @GetMapping
    List<LibroDTO> findAll()
    {
        return bookService.findAll();
    }

    @PostMapping
    LibroDTO save(@Valid @RequestBody LibroDTO bookDTO) { return bookService.save(bookDTO); }

    @DeleteMapping
    void delete(@RequestBody String uuid)
    {
        bookService.deleteByUuid(uuid);
    }

    @PutMapping
    LibroDTO update(@RequestBody LibroDTO bookDTO)
    {
        return bookService.update(bookDTO.getUuid(), bookDTO);
    }

    @PatchMapping
    LibroDTO partialUpdate(@RequestBody LibroDTO bookDTO) {  return bookService.partialUpdate(bookDTO.getUuid(), bookDTO); }

    @GetMapping("/{uuid}")
    LibroDTO getByUuid(@PathVariable String uuid) { return bookService.findByUuid(uuid); }
}

