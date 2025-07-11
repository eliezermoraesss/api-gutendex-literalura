package br.com.eliezer.literalura.repository;

import br.com.eliezer.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
