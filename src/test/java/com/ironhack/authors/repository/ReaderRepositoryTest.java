package com.ironhack.authors.repository;

import com.ironhack.authors.model.authors.*;
import com.ironhack.authors.repository.authors.*;
import com.ironhack.authors.utility.PublicationHandler;
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

@SpringBootTest
@Transactional
class ReaderRepositoryTest {

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

        //READERS
        UserHandler userHandler = new UserHandler();
        Map<String, User> users = userHandler.generateUsers();
        readerRepository.saveAll(userHandler.getReaders());
        Reader felicidad = (Reader) users.get("Felicidad");
        Reader consuelo = (Reader) users.get("Consuelo");

        //PUBLICATIONS
        PublicationHandler publicationHandler = new PublicationHandler();
        List<Publication> publications = publicationHandler.generatePublications();

        //Para facilitar la corrección: Esto son los dos articulos y el primer book.
        felicidad.addPublication(publications.get(0));
        felicidad.addPublication(publications.get(1));
        felicidad.addPublication(publications.get(2));

        //Para facilitar la corrección: Esto son el segundo book y el blogpost.
        consuelo.addPublication(publications.get(3));
        consuelo.addPublication(publications.get(4));

        articleRepository.saveAll(publicationHandler.getArticles());
        blogPostRepository.saveAll(publicationHandler.getBlogPosts());
        bookRepository.saveAll(publicationHandler.getBooks());
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
    void findByFirstNamePublishDateBetween_succesful() {
        LocalDate date2016 = LocalDate.of(2016, 1,1);
        LocalDate date2021 = LocalDate.of(2021, 1,1);

        assertEquals(2, readerRepository.findByFirstNamePublishDateBetween("" +
                "Felicidad",
                date2016,
                date2021).size()
        );
    }

    @Test
    void findByTitleContaining_succesful() {
        assertEquals(2, readerRepository.findByTitleContaining("JPA").size());
    }
}