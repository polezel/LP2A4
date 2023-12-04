package lp2a4.ifa.api.domain.pessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import lp2a4.ifa.api.*;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	@Query("SELECT * FROM Pessoa p WHERE p.id = :id")
	Pessoa buscaPorId(@Param("id") Long id);

	@Query("SELECT * FROM Pessoa p")
	List<Pessoa> findAll();

	@Modifying
	@Query("delete from Pessoa p where p.id=:id")
	void delete(@Param("id") Long id);

}