package controller;

import dto.LibroDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import service.LibroService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class LibroController
{
    private final LibroService bookService;

    @Value("${file.upload.path}")
    private String uploadPath;

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
    LibroDTO update(@RequestBody String uuid, @RequestBody LibroDTO bookDTO)
    {
        return bookService.update(uuid, bookDTO);
    }

    @PatchMapping
    LibroDTO partialUpdate(@RequestBody String uuid, @RequestBody LibroDTO bookDTO)
    {
        return bookService.partialUpdate(uuid, bookDTO);
    }

}

