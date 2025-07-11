package br.com.eliezer.literalura.repository;

import br.com.eliezer.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query(
            value = """
                    SELECT * 
                    FROM autores
                    WHERE ano_de_nascimento <= :ano 
                    AND (ano_de_falecimento IS NULL OR ano_de_falecimento > :ano)""",
            nativeQuery = true
    )
    List<Autor> consultarAutoresVivosPorAno(Integer ano);
}
