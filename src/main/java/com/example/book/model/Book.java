package com.example.book.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book {

@Id
@GeneratedValue(strategy = GenerationType.UUID)

private UUID id;

private String title;

private String author;

private String genre;

private Integer numberOfPages;

private Integer rating;

private Boolean hasRead;




public UUID getId(){
    return id;
}
public String getTitle(){
    return title;
}
public String getAuthor(){
    return author;
}
public String getGenre(){
    return genre;
}
public Integer getNumberOfPages(){
    return numberOfPages;
}
public Integer getRating(){
    return rating;
}
public Boolean getHasRead(){
    return hasRead;
}

public void setId(UUID id){
    this.id = id;
}
public void setTitle(String title){
    this.title = title;
}
public void setAuthor(String author){
    this.author = author;
}
public void setGenre(String genre){
    this.genre = genre;
}
public void setNumberOfPages(Integer numberOfPages){
    this.numberOfPages = numberOfPages;
}
public void setRating(Integer rating){
    this.rating = rating;
}
public void setHasRead(Boolean hasRead){
    this.hasRead = hasRead;
}






}
