package com.ironhack.authors.model.authors;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Article extends Publication {

    private Long numOfRevisions;
    private Long numberOfCitations;
    private String speciality;

}
