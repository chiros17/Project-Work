package it.biblioteca.project_work.prestiti_service.dto;

import java.time.LocalDate;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrestitoDto
{

    private String uuid;

    @NotNull
    @NotBlank
    private String bookUuid;

    @NotNull
    @NotBlank
    private String utenteUuid;

    private LocalDate dataInizioPrestito;

    private LocalDate dataRestituzione;

    private Boolean isRestituito;

}
