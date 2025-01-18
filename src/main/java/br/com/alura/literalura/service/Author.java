package br.com.alura.literalura.service;

import com.google.gson.annotations.SerializedName;

public class Author {

    @SerializedName("name")
    private String name;

    @SerializedName("birth_year")
    private Integer birthYear;

    @SerializedName("death_year")
    private Integer deathYear;

    // Getters e setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }
}

