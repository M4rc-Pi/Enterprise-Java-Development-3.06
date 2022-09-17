package com.ironhack.authors.repository;

import com.ironhack.authors.model.authors.*;
import com.ironhack.authors.repository.authors.*;
import com.ironhack.authors.utility.UserHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BlogPostRepository blogPostRepository;
    @Autowired
    private ArticleRepository articleRepository;


    @BeforeEach
    void setUp() {

        UserHandler userHandler = new UserHandler();
        Map<String, User> users = userHandler.generateUsers();
        authorRepository.saveAll(userHandler.getAuthors());
        readerRepository.saveAll(userHandler.getReaders());
        Author paz = (Author) users.get("Paz");
        Author esperanza = (Author) users.get("Esperanza");
        Reader felicidad = (Reader) users.get("Felicidad");
        Reader consuelo = (Reader) users.get("Consuelo");

        Book javaBook = new Book();
        javaBook.setTitle("Java is Fun");
        javaBook.setNumPages(200);
        javaBook.setPublishingDate(LocalDate.of(2017, 4, 4));

        javaBook.getUsers().add(esperanza);
        esperanza.getCreatedPublications().add(javaBook);

        bookRepository.save(javaBook);

        Book jpaBook = new Book();
        jpaBook.setTitle("JPA is awesome!");
        jpaBook.setNumPages(300);
        jpaBook.setPublishingDate(LocalDate.of(2022, 4, 4));

        //La asociación es bidireccional
        jpaBook.getUsers().add(esperanza);
        jpaBook.getUsers().add(paz);
        esperanza.getCreatedPublications().add(jpaBook);
        paz.getCreatedPublications().add(jpaBook);

        bookRepository.save(jpaBook);

        BlogPost inheritancePost = new BlogPost();
        inheritancePost.setTitle("Inheritance Strategies with JPA and Hibernate – The Complete Guide");
        inheritancePost.setPublishingDate(LocalDate.of(2020, 1, 23));
        inheritancePost.setUrl("https://thorben-janssen.com/complete-guide-inheritance-strategies-jpa-hibernate/#Joined");

        inheritancePost.getUsers().add(paz);
        paz.getCreatedPublications().add(inheritancePost);

        blogPostRepository.save(inheritancePost);

        authorRepository.saveAll(userHandler.getAuthors());
        readerRepository.saveAll(userHandler.getReaders());
    }

    @AfterEach
    public void tearDown() {
        articleRepository.deleteAll();
        bookRepository.deleteAll();
        blogPostRepository.deleteAll();
        authorRepository.deleteAll();
    }

    @Test
    void findByAuthors_FirstNameAndAuthors_LastNameOrderByTitleAsc_successful() {

        // Just for demonstrating purposes
        List<Author> authorList = authorRepository.findAll();

        assertEquals(2,
                bookRepository.findByAuthors_FirstNameAndAuthors_LastNameOrderByTitleAsc("Esperanza","Amor").size());
    }

    @Test
    void findByPublishingDateBetween_successful() {
        assertEquals(1,
                blogPostRepository.findByPublishingDateBetween(
                        LocalDate.of(2019, 1, 23),
                        LocalDate.of(2021, 1, 23)
                ).size());
    }
}