package it.project_work.recensione.recensione_service.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecensioneDto
{
    private String uuid;
    private String contenuto;

    @DecimalMin("1.0")
    @DecimalMax("5.0")
    private Double stars;

    @NotNull
    @NotBlank
    private String libroUuid;

    @NotNull
    @NotBlank
    private String utenteUuid;
    private LocalDateTime dataRecensione;
}
