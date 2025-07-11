package br.com.eliezer.literalura.principal;

import br.com.eliezer.literalura.model.DadosLivro;
import br.com.eliezer.literalura.model.DadosResultado;
import br.com.eliezer.literalura.service.ConsumoApi;
import br.com.eliezer.literalura.service.ConverteDados;

import java.util.Scanner;

public class Principal {
    private static final String ENDERECO = "https://gutendex.com/books/";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private Scanner scan = new Scanner(System.in);

    public void exibeMenu() {
        String opcao = "";

        while (!opcao.equals("0")) {
            System.out.println("----------");
            System.out.println("Escolha o número de sua opção:");
            System.out.println("1 - Buscar livro pelo título");
            System.out.println("2 - Listar livros registrados");
            System.out.println("3 - Listar autores registrados");
            System.out.println("4 - Listar autores vivos em um determinado ano");
            System.out.println("5 - Listar livros em um determinado idioma");
            System.out.println("0 - Sair");
            opcao = scan.nextLine();

            switch (opcao) {
                case "1":
                    buscarLivroApi();
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

    private void buscarLivroApi() {
        DadosLivro livro = getDadosLivro();
        System.out.println("----- LIVRO -----");
        System.out.println("Título: " + livro.titulo());
        System.out.println("Autor: " + livro.autores().getFirst().nome());
        System.out.println("Idioma: " + livro.idiomas().getFirst());
        System.out.println("Número de dowloads: " + livro.numeroDeDownloads());
        System.out.println("-----------------");
        System.out.println();
    }

    private DadosLivro getDadosLivro() {
        System.out.println("Insira o nome do livro que você deseja procurar: ");
        String titulo = scan.nextLine();
        var json = consumoApi.obterDados(ENDERECO + "?search=" + titulo.toLowerCase().replace(" ", "+"));
        DadosResultado resultado = conversor.obterDados(json, DadosResultado.class);
        DadosLivro livro = resultado.results().getFirst();
        return livro;
    }
}
