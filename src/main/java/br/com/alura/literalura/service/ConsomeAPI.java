package br.com.alura.literalura.service;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ConsomeAPI {
    protected final LivroRepository livroRepository;
    protected final AutorRepository autorRepository;

    protected final String ENDERECO = "http://gutendex.com/books/";

    public ConsomeAPI(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public String obterDados(String nomeLivro) {
        // Verificar se o livro já existe no banco de dados
        Book livroExistente = livroRepository.findByTitle(nomeLivro);

        if (livroExistente != null) {
            return "Livro encontrado no banco de dados:\nTítulo: " + livroExistente.getTitle() +
                    "\nAutor: " + livroExistente.getAuthor().getName() +
                    "\nIdioma: " + livroExistente.getLanguages() +
                    "\nNúmero de Downloads: " + livroExistente.getDownloadCount();
        }

        // Consultar a API se o livro não estiver no banco
        String apiUrl = ENDERECO + "?search=" + nomeLivro.replace(" ", "%20");

        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException("Erro na API: Código de status " + response.statusCode());
            }

            Gson gson = new Gson();
            ApiResponse apiResponse = gson.fromJson(response.body(), ApiResponse.class);
            if (apiResponse.getResults() == null || apiResponse.getResults().isEmpty()) {
                throw new RuntimeException("Nenhum livro encontrado.");
            }

            Book firstBook = apiResponse.getResults().get(0);
            String title = firstBook.getTitle();

            String authorName = firstBook.getAuthor() != null && !firstBook.getAuthor().isEmpty()
                    ? firstBook.getAuthor().getName()
                    : "Autor desconhecido";

            String language = String.join(", ", firstBook.getLanguages());
            int numDownloads = firstBook.getDownloadCount();

            // Verificar se o autor já existe no banco de dados
            Author autorExistente = autorRepository.findByName(authorName);
            if (autorExistente == null) {
                autorExistente = new Author();
                autorExistente.setName(authorName);
                autorRepository.save(autorExistente);
            }

            List<String> languages = firstBook.getLanguages();


            Book novoLivro = new Book();
            novoLivro.setTitle(title);
            novoLivro.setAuthor(autorExistente);
            novoLivro.setLanguages(languages);
            novoLivro.setDownloadCount(numDownloads);
            livroRepository.save(novoLivro);

            return "Livro obtido da API e salvo no banco de dados:\nTítulo: " + title +
                    "\nAutor: " + authorName +
                    "\nIdioma: " + language +
                    "\nNúmero de Downloads: " + numDownloads;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter dados da API.", e);
        }
    }
}
