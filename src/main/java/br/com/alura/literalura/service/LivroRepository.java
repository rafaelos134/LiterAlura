package br.com.alura.literalura.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Book, Long> {
    //    @EntityGraph(attributePaths = {"languages", "authors"})
    Book findByTitle(String title);
    List<Book> findByLanguagesContaining(String language);
}