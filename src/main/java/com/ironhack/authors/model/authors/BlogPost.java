package com.ironhack.authors.model.authors;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class BlogPost extends Publication {

	private String url;

}
