package com.ironhack.authors.model.authors;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Author extends User{

	private String speciality;
	@ManyToMany(mappedBy="users")
	private List<Publication> createdPublications = new ArrayList<Publication>();

	public void addPublication(Publication publication) {
		this.createdPublications.add(publication);
		publication.getUsers().add(this);
	}
}