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
class ArticleRepositoryTest {

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

        //USERS
        UserHandler userHandler = new UserHandler();
        Map<String, User> users = userHandler.generateUsers();
        Author paz = (Author) users.get("Paz");
        Author esperanza = (Author) users.get("Esperanza");
        Reader felicidad = (Reader) users.get("Felicidad");
        Reader consuelo = (Reader) users.get("Consuelo");

        //PUBLICATIONS
        PublicationHandler publicationHandler = new PublicationHandler();
        List<Publication> publications = publicationHandler.generatePublications();

        //Añadir bidireccionalmente Articulos con Autores
        paz.addPublication(publicationHandler.getArticles().get(0));
        felicidad.addPublication(publicationHandler.getArticles().get(0));
        consuelo.addPublication(publicationHandler.getArticles().get(0));

        paz.addPublication(publicationHandler.getArticles().get(1));
        consuelo.addPublication(publicationHandler.getArticles().get(1));

        //Guardar
        articleRepository.saveAll(publicationHandler.getArticles());
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
    void findArticles_UsersFirstName_successful() {
        assertEquals(2, articleRepository.findArticles_UsersFirstName("Paz").size());
    }

    @Test
    void findByArticles_TitleContainsAndUser_successful() {
        assertEquals(1, articleRepository.findByArticles_TitleContainsAndUser(
                "Spring",
                "Felicidad",
                "Abundante").size());
    }

    @Test
    void findByArticles_TitleContainsPublishDateAndUser_successful() {
        LocalDate date2021 = LocalDate.of(2021, 1,1);
        LocalDate date2020 = LocalDate.of(2020, 1,1);

        assertEquals(2, articleRepository.findByArticles_TitleContainsPublishDateAndUser(
                "Spring",
                date2020,
                "Paz",
                "Alegria").size());

        assertEquals(1, articleRepository.findByArticles_TitleContainsPublishDateAndUser(
                "Spring",
                date2021,
                "Consuelo",
                "Sabio").size());
    }

    @Test
    void findByArticles_SpecialityContains() {
        assertEquals(2, articleRepository.findBySpecialityContains("Software").size());
    }
}