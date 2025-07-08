package br.com.eliezer.literalura;

import br.com.eliezer.literalura.model.DadosResultado;
import br.com.eliezer.literalura.model.DadosLivro;
import br.com.eliezer.literalura.service.ConsumoApi;
import br.com.eliezer.literalura.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("https://gutendex.com/books/");
		System.out.println(json);
		System.out.println("Escolha o número de sua opção:");

		ConverteDados conversor = new ConverteDados();
		DadosResultado resultado = conversor.obterDados(json, DadosResultado.class);

		for (DadosLivro livro : resultado.results()) {
			System.out.println("Título: " + livro.titulo());
			System.out.println("Autor: " + livro.autores().getFirst().nome());
		}
	}
}
