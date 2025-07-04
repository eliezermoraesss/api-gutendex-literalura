package br.com.eliezer.literalura;

import br.com.eliezer.literalura.service.ConsumoApi;
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
		json = consumoApi.obterDados("https://coffee.alexflipnote.dev/random.json");
		System.out.println(json);
	}
}
