package controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.BookService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController
{
    private final BookService bookService;

    
}
