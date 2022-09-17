package com.ironhack.authors.model.authors;

import javax.persistence.*;

@Entity
public class Article extends Publication {

    private Long numOfRevisions;
    private Long numberOfCitations;
    private String speciality;



    public Long getNumOfRevisions() {
        return numOfRevisions;
    }

    public void setNumOfRevisions(Long numOfRevisions) {
        this.numOfRevisions = numOfRevisions;
    }

    public Long getNumberOfCitations() {
        return numberOfCitations;
    }

    public void setNumberOfCitations(Long numberOfCitations) {
        this.numberOfCitations = numberOfCitations;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
