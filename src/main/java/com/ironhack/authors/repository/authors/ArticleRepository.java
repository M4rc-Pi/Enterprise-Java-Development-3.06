package com.ironhack.authors.repository.authors;

import com.ironhack.authors.model.authors.Article;
import com.ironhack.authors.model.authors.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {


    @Query("select a from Article a inner join a.authors authors " +
            "where authors.firstName = :firstName")
    List<Article> findArticles_AuthorsFirstName (@Param("firstName") String firstName);

/*
    @Query("select a from Article a inner join a.authors authors " +
            "where a.title like concat('%', ?1, '%') " +
            //"and a.publishingDate > ?2" +
            "and authors.firstName = ?3 " +
            "and authors.lastName = ?4 ")
            //"order by a.publishingDate DESC")
    List<Article> findByArticles_TitleContainsPublishDateAndAuthor(String titleContains, String firstName, String lastName);
*/

    @Query("select a from Article a " +
            "where upper(a.speciality) " +
            "like upper(concat('%', :speciality, '%'))")
    List<Article> findBySpecialityContains(@Param("speciality") String speciality);



/*
    @Query("select a from Article a inner join a.authors authors " +
            "having authors.id <= :maxAuthors")
    List<Article> findArticles_HavingThreeOrMoreAuthors (@Param("maxAuthors") String maxAuthors);
*/
}
