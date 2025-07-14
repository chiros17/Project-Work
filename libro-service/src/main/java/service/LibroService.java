package service;

import dto.LibroDTO;

import java.util.List;

public interface LibroService
{
    List<LibroDTO> findAll();
    LibroDTO findByUuid( String uuid );
    LibroDTO save( LibroDTO book );
    LibroDTO update(String uuid, LibroDTO book );
    LibroDTO partialUpdate(String uuid, LibroDTO book );
    void deleteByUuid( String uuid );
}
