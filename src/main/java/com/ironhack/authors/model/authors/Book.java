package com.ironhack.authors.model.authors;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Book extends Publication {

	private int numPages;

}