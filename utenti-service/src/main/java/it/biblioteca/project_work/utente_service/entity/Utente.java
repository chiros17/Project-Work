package it.biblioteca.project_work.utente_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    private String nome;
    private String email;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;
}
