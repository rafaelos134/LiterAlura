package br.com.alura.literalura.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Author, Long> {
    Author findByName(String name);
    List<Author> findByBirthYearLessThanAndDeathYearGreaterThanEqual(int startYear, int endYear);
}