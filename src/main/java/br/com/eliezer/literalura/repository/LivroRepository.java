package br.com.eliezer.literalura.repository;

import br.com.eliezer.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
