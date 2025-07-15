package it.biblioteca.project_work.libro_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LibroDTO
{
    private String uuid;

    @Size(min = 5, max = 50)
    private String titolo;

    @Size(min = 5, max = 100)
    private String autore;

    private String copertina;

    @Min(0)
    private Integer quantita;
    private Double prezzo;
}
