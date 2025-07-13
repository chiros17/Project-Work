package service;

import dto.BookDTO;

import java.util.List;

public interface BookService
{
    List<BookDTO> findAll();
    BookDTO findByUuid( String uuid );
    BookDTO save( BookDTO book );
    BookDTO update(String uuid, BookDTO book );
    BookDTO partialUpdate(String uuid, BookDTO book );
    void deleteByUuid( String uuid );
}
