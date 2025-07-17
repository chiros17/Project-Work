package it.biblioteca.project_work.libro_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.biblioteca.project_work.libro_service.dto.LibroDTO;
import it.biblioteca.project_work.libro_service.service.LibroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/libro")
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

