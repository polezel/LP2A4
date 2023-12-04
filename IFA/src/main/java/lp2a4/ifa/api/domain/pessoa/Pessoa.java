package lp2a4.ifa.api.domain.pessoa;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Table(name = "pessoas")
@Entity(name = "Pessoa")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pessoa implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@Column(name = "nome")
	private String nome;

	public Pessoa(CadastroPessoa cp) {
		this.nome = cp.nome();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void mudarNome(UpdatePessoa up) {
		if (up.nome() != null) {
			this.nome = up.nome();
		}
	}
}
