package it.biblioteca.project_work.libro_service.controller;

import it.biblioteca.project_work.libro_service.dto.LibroDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import it.biblioteca.project_work.libro_service.service.LibroService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/libri")
public class LibroController
{
    private final LibroService bookService;

    @GetMapping
    List<LibroDTO> mostraLibri()
    {
        return bookService.findAll();
    }

    @PostMapping
    LibroDTO save(@RequestBody LibroDTO bookDTO)
    {
        return bookService.save(bookDTO);
    }

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
    LibroDTO partialUpdate(@RequestBody LibroDTO bookDTO)
    {
        return bookService.partialUpdate(bookDTO.getUuid(), bookDTO);
    }

}

