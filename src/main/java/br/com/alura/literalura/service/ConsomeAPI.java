package br.com.alura.literalura.service;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

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
        Book livroExistente = livroRepository.findByTitle(nomeLivro);


        //unico erro, consertando pode enviar
        if (livroExistente != null) {
            return "Livro encontrado no banco de dados:\nTítulo: " + livroExistente.getTitle()+
                    "\nAutor: " + livroExistente.getAuthor().get(0).getName() +
                    "\nIdioma/: " + livroExistente.getLanguages() +
                    "\nNúmero de Downloads: " + livroExistente.getDownloadCount();
        }

        //            StringBuilder resultado = new StringBuilder("Livro encontrado no banco de dados:\n");

        // Adiciona o título
//            resultado.append("Título: ").append(livroExistente.getTitle()).append("\n");

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
            Long authorId = firstBook.getAuthorId();
            String authorName = firstBook.getAuthor().get(0).getName();
            Integer deathYear = firstBook.getAuthor().get(0).getDeathYear();
            Integer birthYear = firstBook.getAuthor().get(0).getBirthYear();

            Author firstAuthor = new Author();
//            firstAuthor = apiResponse.getResults().get(0);
            firstAuthor.setAuthorId(authorId);
            firstAuthor.setName(authorName);
            firstAuthor.setDeathYear(deathYear);
            firstAuthor.setBirthYear(birthYear);



//            String authorName = firstBook.getAuthor().get(0).getName();

            String language = String.join(", ", firstBook.getLanguages());
            int numDownloads = firstBook.getDownloadCount();

            Author autorExistente = autorRepository.findByName(authorName);
            if (autorExistente == null) {
                autorExistente = new Author();
                autorExistente.setAuthorId(authorId);
                autorExistente.setName(authorName);
                autorExistente.setDeathYear(deathYear);
                autorExistente.setBirthYear(birthYear);
                autorRepository.save(autorExistente);
            }


            List<String> languages = firstBook.getLanguages();

            Book libros = livroRepository.findByTitle(nomeLivro);
            if (libros == null) {
            Book novoLivro = new Book();
            novoLivro.setAuthorId(authorId);
            novoLivro.setTitle(title);
//            novoLivro.setAuthor(firstBook.getAuthor());
            novoLivro.setLanguages(languages);
            novoLivro.setDownloadCount(numDownloads);
            livroRepository.save(novoLivro);}


            return "Livro obtido da API e salvo no banco de dados:\nTítulo: " + title +
                    "\nAutor: " + authorName +
                    "\nIdioma: " + language +
                    "\nNúmero de Downloads: " + numDownloads;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter dados da API.", e);
        }
    }

    public String listarLivros() {
        List<Book> livros = livroRepository.findAll();
        if (livros.isEmpty()) {
            return "Nenhum livro encontrado no banco de dados.";
        }
        StringBuilder resultado = new StringBuilder("Livros registrados no banco de dados:\n");
        for (Book livro : livros) {
            resultado.append("- ").append(livro.getTitle()).append("\n");
        }
        return resultado.toString();
    }

    public String listarAutores() {
        List<Author> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            return "Nenhum autor encontrado no banco de dados.";
        }
        StringBuilder resultado = new StringBuilder("Autores registrados no banco de dados:\n");
        for (Author autor : autores) {
            resultado.append("- ").append(autor.getName()).append("\n");
        }
        return resultado.toString();
    }

//    public String listarAutoresVivosPorAno(int ano) {
//        List<Author> autores = autorRepository.findByBirthYearLessThanAndDeathYearGreaterThanEqual(ano, ano);
//        if (autores.isEmpty()) {
//            return "Nenhum autor vivo encontrado no ano " + ano + ".";
//        }
//        StringBuilder resultado = new StringBuilder("Autores vivos no ano ").append(ano).append(":\n");
//        for (Author autor : autores) {
//            resultado.append("- ").append(autor.getName()).append("\n");
//        }
//        return resultado.toString();
//    }

public String listarAutoresVivosPorAno(int ano) {
        // Obtém todos os autores
        List<Author> autores = autorRepository.findAll();

        // Filtra os autores que estão vivos no ano especificado
        List<Author> autoresVivos = autores.stream()
                .filter(autor -> autor.getBirthYear() <= ano && (autor.getDeathYear() == null || autor.getDeathYear() >= ano))
                .toList();

        // Se não houver autores vivos, retorna mensagem adequada
        if (autoresVivos.isEmpty()) {
            return "Nenhum autor vivo encontrado no ano " + ano + ".";
        }

        // Monta a resposta com os autores vivos
        StringBuilder resultado = new StringBuilder("Autores vivos no ano ").append(ano).append(":\n");
        for (Author autor : autoresVivos) {
            resultado.append("- ").append(autor.getName()).append("\n");
        }

        return resultado.toString();}


    public String listarLivrosPorIdioma(String idioma) {
        List<Book> livros = livroRepository.findByLanguagesContaining(idioma);
        if (livros.isEmpty()) {
            return "Nenhum livro encontrado no idioma: " + idioma + ".";
        }
        StringBuilder resultado = new StringBuilder("Livros no idioma ").append(idioma).append(":\n");
        for (Book livro : livros) {
            resultado.append("- ").append(livro.getTitle()).append("\n");
        }
        return resultado.toString();
    }
}
