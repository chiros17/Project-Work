package dto;

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
    private String titolo;
    private String autore;
    private String copertina;
    private Integer quantita;
    private Double prezzo;
}
