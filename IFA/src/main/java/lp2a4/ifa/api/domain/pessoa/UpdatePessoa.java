package lp2a4.ifa.api.domain.pessoa;

import jakarta.validation.constraints.NotNull;

public record UpdatePessoa(@NotNull Long id, String nome) {

}
