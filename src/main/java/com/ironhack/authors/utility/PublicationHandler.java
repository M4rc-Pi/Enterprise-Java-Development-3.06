package com.ironhack.authors.utility;

import com.ironhack.authors.model.authors.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Data
public class PublicationHandler {
    private List<Publication> publications;
    private List<Article> articles;
    private List<BlogPost> blogPosts;
    private List<Book> books;

    public List<Publication> generatePublications() {
        publications = new ArrayList<>();
        articles = generateArticles();
        blogPosts = generateBlogPosts();
        books = generateBooks();

        publications = Stream.concat(publications.stream(), articles.stream()).toList();
        publications = Stream.concat(publications.stream(), blogPosts.stream()).toList();
        publications = Stream.concat(publications.stream(), books.stream()).toList();
        return publications;
    }

    private List<Book> generateBooks() {

        //JAVABOOK
        Book javaBook = new Book();
        javaBook.setTitle("Java is Fun");
        javaBook.setNumPages(200);
        javaBook.setPublishingDate(LocalDate.of(2017, 4, 4));

        //JPABOOK
        Book jpaBook = new Book();
        jpaBook.setTitle("JPA is awesome!");
        jpaBook.setNumPages(300);
        jpaBook.setPublishingDate(LocalDate.of(2022, 4, 4));

        return books = new ArrayList<>() {
            {
                add(javaBook);
                add(jpaBook);
            }
        };
    }

    private List<BlogPost> generateBlogPosts() {

        //INHERITANCEPOST
        BlogPost inheritancePost = new BlogPost();
        inheritancePost.setTitle("Inheritance Strategies with JPA and Hibernate – The Complete Guide");
        inheritancePost.setPublishingDate(LocalDate.of(2020, 1, 23));
        inheritancePost.setUrl("https://thorben-janssen.com/complete-guide-inheritance-strategies-jpa-hibernate/#Joined");

        return blogPosts = new ArrayList<>() {
            {
                add(inheritancePost);
            }
        };
    }

    public List<Article> generateArticles() {

        //ARTICULO 1
        Article article1 = new Article();
        article1.setTitle("JPA Spring");
        article1.setPublishingDate(LocalDate.of(2020, 3, 28));
        article1.setNumOfRevisions(101L);
        article1.setNumberOfCitations(45L);
        article1.setSpeciality("Software Development");

        //ARTICULO 2
        Article article2 = new Article();
        article2.setTitle("Spring Framework Magic For Dummies");
        article2.setPublishingDate(LocalDate.of(2022, 7, 12 ));
        article2.setNumOfRevisions(2567L);
        article2.setNumberOfCitations(450L);
        article2.setSpeciality("Software Development");

        return articles = new ArrayList<>() {
            {
                add(article1);
                add(article2);
            }
        };
    }
}
