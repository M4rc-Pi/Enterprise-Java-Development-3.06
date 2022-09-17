package com.ironhack.authors.model.authors;

import com.ironhack.authors.enums.ReaderSpeciality;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Reader extends User {

    @Column(columnDefinition = "ENUM('BEGINNER', 'ADVANCED', 'PROFESSIONAL')")
    @Enumerated(EnumType.STRING)
    private ReaderSpeciality profile;

    @ManyToMany(mappedBy="users")
    private List<Publication> readPublications = new ArrayList<Publication>();

    public void addPublication(Publication publication) {
        this.readPublications.add(publication);
        publication.getUsers().add(this);
    }
}
