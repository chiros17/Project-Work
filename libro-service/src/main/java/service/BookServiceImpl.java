package service;

import dto.BookDTO;
import exception.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import model.Book;
import org.springframework.stereotype.Service;
import repository.BookRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService
{
    private final BookRepository bookRepository;
    // private final WebClient.Builder webClientBuilder;

    @Override
    public List<BookDTO> findAll()
    {
        return bookRepository.findAll()
                                        .stream()
                                        .map(this::modelToDto)
                                        .toList();
    }

    @Override
    public BookDTO findByUuid(String uuid)
    {
        BookDTO bookDTO = modelToDto(bookRepository.findByUuid(uuid).orElseThrow(BookNotFoundException::new));
        return bookDTO;
    }

    @Override
    public BookDTO save(BookDTO book)
    {
        book.setUuid(UUID.randomUUID().toString());
        return modelToDto( bookRepository.save( dtoToModel( book ) ) );
    }

    @Override
    public BookDTO update(String uuid, BookDTO book)
    {
        Book bookToUpdate = bookRepository.findByUuid( uuid ).orElseThrow(BookNotFoundException::new);

        bookToUpdate.setAutore(book.getAutore());
        bookToUpdate.setTitolo(book.getTitolo());
        bookToUpdate.setPrezzo(book.getPrezzo());

        return modelToDto( bookRepository.save( bookToUpdate ) );
    }

    @Override
    public BookDTO partialUpdate(String uuid, BookDTO book)
    {
        Book bookToUpdate = bookRepository.findByUuid( uuid ).orElseThrow(BookNotFoundException::new);

        if( book.getAutore() != null)
            bookToUpdate.setAutore(book.getAutore());

        if( book.getTitolo() != null)
            bookToUpdate.setTitolo(book.getTitolo());

        if( book.getPrezzo() != null)
            bookToUpdate.setPrezzo(book.getPrezzo());

        return modelToDto( bookRepository.save( bookToUpdate ) );
    }

    @Override
    public void deleteByUuid(String uuid)
    {
        Book bookToDelete = bookRepository.findByUuid( uuid ).orElseThrow(BookNotFoundException::new);
        bookRepository.deleteById(bookToDelete.getId());
    }

    private BookDTO modelToDto( Book book )
    {
        return BookDTO.builder()
                .uuid(book.getUuid())
                .autore(book.getAutore())
                .titolo(book.getTitolo())
                .prezzo(book.getPrezzo())
                .build();
    }

    private Book dtoToModel( BookDTO book )
    {
        return Book.builder()
                .uuid(book.getUuid())
                .autore(book.getAutore())
                .titolo(book.getTitolo())
                .prezzo(book.getPrezzo())
                .build();
    }
}
