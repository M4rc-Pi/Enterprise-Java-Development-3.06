package com.ironhack.authors.utility;

import com.ironhack.authors.model.authors.Author;
import com.ironhack.authors.model.authors.Reader;
import com.ironhack.authors.model.authors.User;
import com.ironhack.authors.repository.authors.AuthorRepository;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class UserHandler {
    private Map<String, User> users;
    private List<Author> authors;
    private List<Reader> readers;

    public Map<String, User> generateUsers() {

        authors = generateAuthors();
        readers = generateReaders();

        return users = new HashMap<>() {{
            put("Paz", authors.get(0));
            put("Esperanza", authors.get(1));
            put("Felicidad", readers.get(0));
            put("Consuelo", readers.get(1));
        }};
    }

    public List<Author> generateAuthors() {
        Author paz = new Author();
        paz.setFirstName("Paz");
        paz.setLastName("Alegria");

        Author esperanza = new Author();
        esperanza.setFirstName("Esperanza");
        esperanza.setLastName("Amor");

        return authors = new ArrayList<>() {
            {
                add(paz);
                add(esperanza);
            }
        };
    }

    public List<Reader> generateReaders() {
        Reader felicidad = new Reader();
        felicidad.setFirstName("Felicidad");
        felicidad.setLastName("Abundante");

        Reader consuelo = new Reader();
        consuelo.setFirstName("Consuelo");
        consuelo.setLastName("Sabio");

        return readers = new ArrayList<>() {
            {
                add(felicidad);
                add(consuelo);
            }
        };
    }
}
