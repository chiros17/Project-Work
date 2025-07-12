package it.biblioteca.project_work.utente_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity // Indica che questa classe è un'entità persistente.
@Builder
@NoArgsConstructor // Sono annotazioni Lombok che generano automaticamente costruttori
@AllArgsConstructor
@Getter
@Setter // riducono il codice boilerplate.
public class Utente {

    @Id // Marca il campo id come chiave primaria della tabella.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifica che il valore dell'id sarà generato automaticamente
                                                        // dal database (es. auto-incremento).
    private Long id;

    private String uuid;

    private String nome;
    private String email;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING) // Indica che Ruolo sarà salvato come stringa
    private Ruolo ruolo;
}
