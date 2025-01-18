package br.com.alura.literalura.service;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class ConsomeAPI {
    protected final String ENDERECO = "http://gutendex.com/books/";

    public String obterDados(String nomeLivro) {

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
            String author = firstBook.getAuthors() != null && !firstBook.getAuthors().isEmpty()
                    ? firstBook.getAuthors().get(0).getName()
                    : "Autor desconhecido";
            String language = String.join(", ", firstBook.getLanguages());
            String numDowloads = String.valueOf(firstBook.getDownloadCount());


            return "Título: " + title + "\nAutor: " + author + "\nLingua: " + language + "\nNúmero de Dowloads: " + numDowloads;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter dados da API.", e);
        }
    }
}
