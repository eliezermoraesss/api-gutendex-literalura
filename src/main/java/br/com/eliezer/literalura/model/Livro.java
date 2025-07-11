package br.com.eliezer.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    private String idioma;
    private Integer numeroDeDownloads;

    public Livro(DadosLivro dados) {
        this.titulo = dados.titulo();
        this.idioma = dados.idiomas().isEmpty() ? null : dados.idiomas().get(0);
        this.numeroDeDownloads = dados.numeroDeDownloads();
        if (dados.autores() != null && !dados.autores().isEmpty()) {
            this.autor = new Autor(dados.autores().getFirst());
        }
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Livro() {
    }

    public Livro(String titulo, Autor autor, String idioma, Integer numeroDeDownloads) {
        this.titulo = titulo;
        this.autor = autor;
        this.idioma = idioma;
        this.numeroDeDownloads = numeroDeDownloads;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDeDownloads() {
        return numeroDeDownloads;
    }

    public void setNumeroDeDownloads(Integer numeroDeDownloads) {
        this.numeroDeDownloads = numeroDeDownloads;
    }
}
