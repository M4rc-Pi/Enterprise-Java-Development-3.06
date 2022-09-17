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


    @Query("select a from Article a inner join a.users users " +
            "where users.firstName = :firstName")
    List<Article> findArticles_UsersFirstName (@Param("firstName") String firstName);

    @Query("select a from Article a inner join a.users users " +
            "where a.title like concat('%', :title, '%') " +
            "and users.firstName = :firstName " +
            "and users.lastName = :lastName ")
    List<Article> findByArticles_TitleContainsAndUser(
            @Param("title") String title,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName);


    @Query("select a from Article a inner join a.users users " +
            "where a.title like concat('%', ?1, '%') " +
            "and a.publishingDate > ?2 " +
            "and users.firstName = ?3 " +
            "and users.lastName = ?4 " +
            "order by a.publishingDate DESC")
    List<Article> findByArticles_TitleContainsPublishDateAndUser(
            String title,
            LocalDate publishingDate,
            String firstName,
            String lastName);


    @Query("select a from Article a " +
            "where upper(a.speciality) " +
            "like upper(concat('%', :speciality, '%'))")
    List<Article> findBySpecialityContains(@Param("speciality") String speciality);

}
