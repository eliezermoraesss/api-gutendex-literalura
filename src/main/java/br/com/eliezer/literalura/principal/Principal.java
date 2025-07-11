package br.com.eliezer.literalura.principal;

import br.com.eliezer.literalura.model.Autor;
import br.com.eliezer.literalura.model.DadosLivro;
import br.com.eliezer.literalura.model.DadosResultado;
import br.com.eliezer.literalura.model.Livro;
import br.com.eliezer.literalura.repository.AutorRepository;
import br.com.eliezer.literalura.repository.LivroRepository;
import br.com.eliezer.literalura.service.ConsumoApi;
import br.com.eliezer.literalura.service.ConverteDados;

import java.util.Scanner;

public class Principal {
    private static final String ENDERECO = "https://gutendex.com/books/";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private Scanner scan = new Scanner(System.in);
    private LivroRepository livroRepository;
    private AutorRepository autorRepository;

    public Principal(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

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
                    listarLivrosRegistrados();
                    break;
                case "3":
                    listarAutoresRegistrados();
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

    private void listarLivrosRegistrados() {
        livroRepository.findAll().forEach(livro -> {
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor().getNome());
            System.out.println("Idioma: " + livro.getIdioma());
            System.out.println("Número de downloads: " + livro.getNumeroDeDownloads());
            System.out.println("-----------------");
        });
    }

    private void listarAutoresRegistrados() {
        autorRepository.findAll().forEach(autor -> {
            System.out.println("Nome: " + autor.getNome());
            System.out.println("Ano de nascimento: " + autor.getAnoDeNascimento());
            System.out.println("Ano de falecimento: " + autor.getAnoDeFalecimento());
            System.out.println("Livros registrados: [" + autor.getLivros().getFirst().getTitulo() + "]");
            System.out.println("-----------------");
        });
    }

    private void buscarLivroApi() {
        DadosLivro dados = getDadosLivro();

        if (dados == null) {
            System.out.println("Nenhum livro encontrado.");
            return;
        }
        System.out.println("----- LIVRO -----");
        System.out.println("Título: " + dados.titulo());
        System.out.println("Autor: " + dados.autores().getFirst().nome());
        System.out.println("Idioma: " + dados.idiomas().getFirst());
        System.out.println("Número de dowloads: " + dados.numeroDeDownloads());
        System.out.println("-----------------");
        System.out.println();
        Livro livro = new Livro(dados);
        autorRepository.save(livro.getAutor());
        livroRepository.save(livro);
    }

    private DadosLivro getDadosLivro() {
        System.out.println("Insira o nome do livro que você deseja procurar: ");
        String titulo = scan.nextLine();
        var json = consumoApi.obterDados(ENDERECO + "?search=" + titulo.toLowerCase().replace(" ", "+"));
        DadosResultado resultado = conversor.obterDados(json, DadosResultado.class);

        if (resultado.results().isEmpty()) {
            return null;
        }

        return resultado.results().getFirst();
    }
}
