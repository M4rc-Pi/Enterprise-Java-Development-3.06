package com.ironhack.authors.repository;

import com.ironhack.authors.model.authors.Article;
import com.ironhack.authors.model.authors.Author;
import com.ironhack.authors.model.authors.BlogPost;
import com.ironhack.authors.model.authors.Book;
import com.ironhack.authors.repository.authors.ArticleRepository;
import com.ironhack.authors.repository.authors.AuthorRepository;
import com.ironhack.authors.repository.authors.BlogPostRepository;
import com.ironhack.authors.repository.authors.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BlogPostRepository blogPostRepository;
    @Autowired
    private ArticleRepository articleRepository;


    @BeforeEach
    void setUp() {

        Author paz = new Author();
        paz.setFirstName("Paz");
        paz.setLastName("Alegria");

        authorRepository.save(paz);

        Author esperanza = new Author();
        esperanza.setFirstName("Esperanza");
        esperanza.setLastName("Amor");

        authorRepository.save(esperanza);

        Author felicidad = new Author();
        felicidad.setFirstName("Felicidad");
        felicidad.setLastName("Abundante");

        authorRepository.save(felicidad);

        Author consuelo = new Author();
        consuelo.setFirstName("Consuelo");
        consuelo.setLastName("Sabio");

        authorRepository.save(consuelo);

        Book javaBook = new Book();
        javaBook.setTitle("Java is Fun");
        javaBook.setNumPages(200);
        javaBook.setPublishingDate(LocalDate.of(2017, 4, 4));

        javaBook.getAuthors().add(esperanza);
        esperanza.getPublications().add(javaBook);

        bookRepository.save(javaBook);

        Book jpaBook = new Book();
        jpaBook.setTitle("JPA is awesome!");
        jpaBook.setNumPages(300);
        jpaBook.setPublishingDate(LocalDate.of(2022, 4, 4));

        //La asociación es bidireccional
        jpaBook.getAuthors().add(esperanza);
        jpaBook.getAuthors().add(paz);
        esperanza.getPublications().add(jpaBook);
        paz.getPublications().add(jpaBook);

        bookRepository.save(jpaBook);

        BlogPost inheritancePost = new BlogPost();
        inheritancePost.setTitle("Inheritance Strategies with JPA and Hibernate – The Complete Guide");
        inheritancePost.setPublishingDate(LocalDate.of(2020, 1, 23));
        inheritancePost.setUrl("https://thorben-janssen.com/complete-guide-inheritance-strategies-jpa-hibernate/#Joined");

        inheritancePost.getAuthors().add(paz);
        paz.getPublications().add(inheritancePost);

        blogPostRepository.save(inheritancePost);

        //ARTICULO 1
        Article article1 = new Article();
        article1.setTitle("JPA Spring");
        article1.setPublishingDate(LocalDate.of(2020, 3, 28));
        article1.setNumOfRevisions(101L);
        article1.setNumberOfCitations(45L);
        article1.setSpeciality("Software Development");

        paz.addPublication(article1);
/*
        //Articulo-Autores
        article1.getAuthors().add(felicidad);
        article1.getAuthors().add(consuelo);
        article1.getAuthors().add(paz);

        //Autores-Articulo
        felicidad.getPublications().add(article1);
        consuelo.getPublications().add(article1);
        paz.getPublications().add(article1);
*/
        articleRepository.save(article1);

        //ARTICULO 2
        Article article2 = new Article();
        article2.setTitle("Spring Framework Magic For Dummies");
        article2.setPublishingDate(LocalDate.of(2022, 7, 12 ));
        article2.setNumOfRevisions(2567L);
        article2.setNumberOfCitations(450L);
        article2.setSpeciality("Software Development");

        //Articulo-Autores
        article2.getAuthors().add(consuelo);
        article2.getAuthors().add(paz);

        //Autores-Articulo
        consuelo.getPublications().add(article2);
        paz.getPublications().add(article2);

        articleRepository.save(article2);

        authorRepository.save(paz);

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


    @Test
    void findArticles_AuthorsFirstName_successful() {
        assertEquals(2, articleRepository.findArticles_AuthorsFirstName("Paz").size());
    }

/*
    @Test
    void findByArticles_TitleContainsPublishDateAndAuthor_successful() {
        //LocalDate date = LocalDate.of(2021, 1,1);
        assertEquals(1, articleRepository.findByArticles_TitleContainsPublishDateAndAuthor(
                "Spring",
                //LocalDate.of(2021, 1,1),
                "Paz",
                "Alegria").size());
    }
*/
    @Test
    void findByArticles_SpecialityContains() {
        assertEquals(2, articleRepository.findBySpecialityContains("Spring").size());
    }
}