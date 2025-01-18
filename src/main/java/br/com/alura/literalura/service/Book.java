package br.com.alura.literalura.service;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Book {

    @SerializedName("title")
    private String title;

    @SerializedName("authors")
    private List<Author> authors;

    @SerializedName("languages")
    private List<String> languages;

    @SerializedName("download_count")
    private int downloadCount;

    // Getters e setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }
}

