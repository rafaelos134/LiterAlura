package br.com.alura.literalura;


//import br.com.alura.literalura.service.AutorRepository;
//import br.com.alura.literalura.service.LivroRepository;
import br.com.alura.literalura.service.ConsomeAPI;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;
import java.util.Scanner;



@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private ConsomeAPI consomeAPI;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Escolha uma das opções:");
			System.out.println("1 - Buscar livro por título");
			System.out.println("2 - Listar livros registrados");
			System.out.println("3 - Listar autores registrados");
			System.out.println("4 - Listar autores vivos em um determinado ano");
			System.out.println("5 - Listar livros em um determinado idioma");
			System.out.println("6 - Finalizar programa");

			System.out.print("Digite sua escolha: ");
			int escolha = scanner.nextInt();
			scanner.nextLine();

			if (escolha == 6) {
				System.out.println("Programa finalizado. Até logo!");
				break;
			}

			switch (escolha) {
				case 1 -> {
					while (true) {
						try {
							System.out.print("Digite o título do livro: ");
							String titulo = scanner.nextLine();

							if (Objects.equals(titulo, "f")) break;

							var json = consomeAPI.obterDados(titulo);
							System.out.println("=========================================");
							System.out.println(json);
							System.out.println("=========================================");
							break; // Sai do loop se não houver exceção
						} catch (RuntimeException e) {
							System.out.println("Erro ao obter dados da API: " + e.getMessage());
							System.out.println("Por favor, tente novamente.");
						}
					}
				}
				case 2 -> {
					var json = consomeAPI.listarLivros();
					System.out.println("=========================================");
					System.out.println(json);
					System.out.println("=========================================");
				}
				case 3 -> {
					var json = consomeAPI.listarAutores();
					System.out.println("=========================================");
					System.out.println(json);
					System.out.println("=========================================");
				}
				case 4 -> {
					System.out.print("Digite o ano: ");
					int ano = scanner.nextInt();
					scanner.nextLine();
					var json = consomeAPI.listarAutoresVivosPorAno(ano);
					System.out.println("=========================================");
					System.out.println(json);
					System.out.println("=========================================");
				}
				case 5 -> {
					System.out.print("Digite o idioma: ");
					String idioma = scanner.nextLine();
					var json = consomeAPI.listarLivrosPorIdioma(idioma);
					System.out.println("=========================================");
					System.out.println(json);
					System.out.println("=========================================");
				}

				default -> System.out.println("Opção inválida. Tente novamente.");
			}
		}

		scanner.close();
























	}
}
