package lp2a4.ifa.api.domain.pessoa;


public record ListagemPessoa(Long id, String nome) {
	public ListagemPessoa(Pessoa pessoa) {
		this(pessoa.getId(), pessoa.getNome());
	}

}
