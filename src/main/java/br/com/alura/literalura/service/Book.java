package br.com.alura.literalura.service;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;

import java.util.List;


//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;

@Entity
public class Book  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @SerializedName("title")
    private String title;

    @SerializedName("languages")
//    @ManyToOne
    @ElementCollection
    private List<String> languages;

    @SerializedName("download_count")
    private int downloadCount;

    @SerializedName("author_id")
    private Long authorId;


    //    @JoinColumn(name = "author")
//    private Author author;

//    @ManyToOne()
    @ElementCollection
    @SerializedName("authors")
    private List<Author> author;

    // Getters e setters
//    public Long getId() {
//        return id;
//    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

//    public Author getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(Author author) {
//        this.author = author;
//    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getAuthorId() {
        return authorId;
    }
}
