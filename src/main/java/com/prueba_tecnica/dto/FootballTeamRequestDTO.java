package com.prueba_tecnica.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@Data
public class FootballTeamRequestDTO {
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre solo puede contener letras y espacios")
    @Schema(description = "Nombre del equipo de futbol", example = "Boca Juniors")
    private String nombre;

    @NotBlank(message = "La liga no puede estar vacía")
    @Size(max = 100, message = "La liga no puede tener más de 100 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "La liga solo puede contener letras y espacios")
    @Schema(description = "Liga del equipo de futbol", example = "Liga Argentina")
    private String liga;

    @NotBlank(message = "El país no puede estar vacío")
    @Size(max = 50, message = "El país no puede tener más de 50 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El pais solo puede contener letras y espacios")
    @Schema(description = "Pais del equipo de futbol", example = "Argentina")
    private String pais;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLiga() {
        return liga;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
