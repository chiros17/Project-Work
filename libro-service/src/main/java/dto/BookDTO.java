package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO
{
    private String uuid;
    private String titolo;
    private String autore;
    private Integer quantita;
    private Double prezzo;
}
