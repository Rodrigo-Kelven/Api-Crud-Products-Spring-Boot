package br.com.dicume.springboot.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRecordDto(
        @NotBlank String name, 
        @NotNull BigDecimal price,
        @NotNull String description,
        @NotNull String supplier,
        @NotNull String category) implements Serializable {

    private static final long serialVersionUID = 1L;
}
