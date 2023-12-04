package lp2a4.ifa.api.domain.pessoa;

import jakarta.validation.constraints.NotBlank;

public record CadastroPessoa(@NotBlank String nome) {

}