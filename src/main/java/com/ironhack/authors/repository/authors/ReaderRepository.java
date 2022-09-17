package com.ironhack.authors.repository.authors;

import com.ironhack.authors.model.authors.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReaderRepository extends JpaRepository<Reader, Long> {

    @Query("""
            select r from Reader r 
            inner join r.readPublications readPublications
            where upper(r.firstName) = upper(?1) 
            and readPublications.publishingDate 
            between ?2 and ?3
            order by readPublications.publishingDate DESC""")
    List<Reader> findByFirstNamePublishDateBetween(
            String firstName,
            LocalDate publishingDateStart,
            LocalDate publishingDateEnd);

    @Query("""
            select r from Reader r 
            inner join r.readPublications readPublications
            where upper(readPublications.title) 
            like upper(concat(?1, '%'))""")
    List<Reader> findByTitleContaining(String title);


}
