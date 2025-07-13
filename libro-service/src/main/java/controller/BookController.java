package controller;

import dto.BookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController
{
    private final BookService bookService;

    @GetMapping
    List<BookDTO> mostraLibri()
    {
        return bookService.findAll();
    }

    @PostMapping
    BookDTO save(@RequestBody BookDTO bookDTO)
    {
        return bookService.save(bookDTO);
    }

    @DeleteMapping
    void delete(@RequestBody String uuid)
    {
        bookService.deleteByUuid(uuid);
    }

    @PutMapping
    BookDTO update(@RequestBody String uuid, @RequestBody BookDTO bookDTO)
    {
        return bookService.update(uuid, bookDTO);
    }

    @PatchMapping
    BookDTO partialUpdate(@RequestBody String uuid, @RequestBody BookDTO bookDTO)
    {
        return bookService.partialUpdate(uuid, bookDTO);
    }
}
