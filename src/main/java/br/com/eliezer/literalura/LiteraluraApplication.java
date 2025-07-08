package br.com.eliezer.literalura;

import br.com.eliezer.literalura.model.DadosResultado;
import br.com.eliezer.literalura.model.DadosLivro;
import br.com.eliezer.literalura.service.ConsumoApi;
import br.com.eliezer.literalura.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String opcao = "";

		while (opcao != "0") {
			System.out.println("----------");
			System.out.println("Escolha o número de sua opção:");
			System.out.println("1 - buscar livro pelo título");
			System.out.println("2 - listar livros registrados");
			System.out.println("3 - listar autores registrados");
			System.out.println("4 - listar autores vivos em um determinado ano");
			System.out.println("5 - listar livros em um determinado idioma");
			System.out.println("0 - sair");
			Scanner scan = new Scanner(System.in);

			opcao = scan.nextLine();

			switch (opcao) {
				case "1":
					String ENDERECO = "https://gutendex.com/books/";
					System.out.println("Insira o nome do livro que você deseja procurar: ");
					String titulo = scan.nextLine();

					var consumoApi = new ConsumoApi();
					var json = consumoApi.obterDados(ENDERECO + "?search=" + titulo.toLowerCase().replace(" ", "+"));
					// System.out.println(json);

					ConverteDados conversor = new ConverteDados();
					DadosResultado resultado = conversor.obterDados(json, DadosResultado.class);
					System.out.println("----- LIVRO -----");
					for (DadosLivro livro : resultado.results()) {
						System.out.println("Título: " + livro.titulo());
						System.out.println("Autor: " + livro.autores().getFirst().nome());
						System.out.println("Idioma: " + livro.idiomas().getFirst());
						System.out.println("Número de dowloads: " + livro.numeroDeDownloads());
					}
					System.out.println("-----------------");
					System.out.println();
					break;
				case "2":
					System.out.println("Opção 2 selecionada: listar livros registrados");
					// Implementar lógica para listar livros registrados
					break;
				case "3":
					System.out.println("Opção 3 selecionada: listar autores registrados");
					// Implementar lógica para listar autores registrados
					break;
				case "4":
					System.out.println("Opção 4 selecionada: listar autores vivos em um determinado ano");
					// Implementar lógica para listar autores vivos em um determinado ano
					break;
				case "5":
					System.out.println("Opção 5 selecionada: listar livros em um determinado idioma");
					// Implementar lógica para listar livros em um determinado idioma
					break;
				case "0":
					System.out.println("Saindo...");
					return;
				default:
					System.out.println("Opção inválida. Tente novamente.");
			}
		}
	}
}
