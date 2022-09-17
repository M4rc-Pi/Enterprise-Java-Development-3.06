package com.ironhack.authors.model.authors;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Version
	private int version;

	private String firstName;

	private String lastName;

	@ManyToMany(mappedBy="authors")
	private List<Publication> publications = new ArrayList<Publication>();

	public void addPublication(Publication publication) {
		this.publications.add(publication);
		publication.getAuthors().add(this);
	}
}