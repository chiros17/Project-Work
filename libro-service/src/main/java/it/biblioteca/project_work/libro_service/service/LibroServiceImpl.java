package it.biblioteca.project_work.libro_service.service;

import it.biblioteca.project_work.libro_service.dto.LibroDTO;
import it.biblioteca.project_work.libro_service.exception.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import it.biblioteca.project_work.libro_service.model.Libro;
import org.springframework.stereotype.Service;
import it.biblioteca.project_work.libro_service.repository.LibroRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LibroServiceImpl implements LibroService
{
    private final LibroRepository libroRepository;
    // private final WebClient.Builder webClientBuilder;


    @Override
    public List<LibroDTO> findAll()
    {
        return libroRepository.findAll()
                                        .stream()
                                        .map(this::modelToDto)
                                        .toList();
    }

    @Override
    public LibroDTO findByUuid(String uuid)
    {
        LibroDTO libroDTO = modelToDto(libroRepository.findByUuid(uuid).orElseThrow(BookNotFoundException::new));
        return libroDTO;
    }

    @Override
    public LibroDTO save(LibroDTO book)
    {
        book.setUuid(UUID.randomUUID().toString());
        return modelToDto( libroRepository.save( dtoToModel( book ) ) );
    }

    @Override
    public LibroDTO update(String uuid, LibroDTO book)
    {
        Libro bookToUpdate = libroRepository.findByUuid( uuid ).orElseThrow(BookNotFoundException::new);

        bookToUpdate.setAutore(book.getAutore());
        bookToUpdate.setTitolo(book.getTitolo());
        bookToUpdate.setPrezzo(book.getPrezzo());

        return modelToDto( libroRepository.save( bookToUpdate ) );
    }

    @Override
    public LibroDTO partialUpdate(String uuid, LibroDTO book)
    {
        Libro bookToUpdate = libroRepository.findByUuid( uuid ).orElseThrow(BookNotFoundException::new);

        if( book.getAutore() != null)
            bookToUpdate.setAutore(book.getAutore());

        if( book.getTitolo() != null)
            bookToUpdate.setTitolo(book.getTitolo());

        if( book.getPrezzo() != null)
            bookToUpdate.setPrezzo(book.getPrezzo());

        return modelToDto( libroRepository.save( bookToUpdate ) );
    }

    @Override
    public void deleteByUuid(String uuid)
    {
        Libro bookToDelete = libroRepository.findByUuid( uuid ).orElseThrow(BookNotFoundException::new);
        libroRepository.deleteById(bookToDelete.getId());
    }

    // Metodi di conversione:

    // Da Libro a LibroDTO
    private LibroDTO modelToDto( Libro book )
    {
        return LibroDTO.builder()
                .uuid(book.getUuid())
                .autore(book.getAutore())
                .titolo(book.getTitolo())
                .copertina(book.getCopertina())
                .prezzo(book.getPrezzo())
                .build();
    }

    // Da LibroDTO a Libro
    private Libro dtoToModel( LibroDTO book )
    {
        return Libro.builder()
                .uuid(book.getUuid())
                .autore(book.getAutore())
                .titolo(book.getTitolo())
                .copertina(book.getCopertina())
                .prezzo(book.getPrezzo())
                .build();
    }
}
