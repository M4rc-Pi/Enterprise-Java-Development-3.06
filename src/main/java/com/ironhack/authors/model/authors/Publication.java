package com.ironhack.authors.model.authors;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Publication {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Version
	private int version;

	private String title;

	private LocalDate publishingDate;
	
	@ManyToMany
	@JoinTable(
		      name="PublicationAuthor",
		      joinColumns={@JoinColumn(name="publicationId", referencedColumnName="id")},
		      inverseJoinColumns={@JoinColumn(name="authorId", referencedColumnName="id")})
	private Set<User> users = new HashSet<User>();

}
