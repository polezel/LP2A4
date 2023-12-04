package lp2a4.ifa.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import lp2a4.ifa.api.domain.pessoa.*;
@RestController
@RequestMapping("pessoas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PessoaController {

	PessoaRepository repository;

	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid CadastroPessoa cp, UriComponentsBuilder uriBuilder) {
		var pessoa = new Pessoa(cp);
		repository.save(pessoa);

		var uri = uriBuilder.path("/pessoas/{id}").buildAndExpand(pessoa.getId()).toUri();

		return ResponseEntity.created(uri).body(new ListagemPessoa(pessoa));
	}

	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		var pessoa = repository.buscaPorId(id);
		return ResponseEntity.ok(new ListagemPessoa(pessoa));
	}

	@GetMapping
	public ResponseEntity<List<ListagemPessoa>> listar() {
		var page = repository.findAll().stream().map(ListagemPessoa::new).toList();
		return ResponseEntity.ok(page);

	}

	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid UpdatePessoa dados) {
		var pessoa = repository.getReferenceById(dados.id());
		pessoa.mudarNome(dados);

		return ResponseEntity.ok(new ListagemPessoa(pessoa));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		var pessoa = repository.getReferenceById(id);
		repository.delete(id);

		return ResponseEntity.noContent().build();
	}

}
