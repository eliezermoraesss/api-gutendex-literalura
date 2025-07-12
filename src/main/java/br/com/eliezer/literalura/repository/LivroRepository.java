package br.com.eliezer.literalura.repository;

import br.com.eliezer.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByTitulo(String titulo);
    List<Livro> findByIdioma(String idioma);
}
