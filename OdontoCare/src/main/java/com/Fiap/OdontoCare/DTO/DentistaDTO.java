package com.Fiap.OdontoCare.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class DentistaDTO {


    private Long idDentista;

    @NotBlank
    @Size(max = 100)
    private String nome;

    @NotBlank
    private String cro;

    @NotBlank
    private String especialidade;

    @NotBlank
    private String telefone;

    private List<Long> consultasIds;
}
