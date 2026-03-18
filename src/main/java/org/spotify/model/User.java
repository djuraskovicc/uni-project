package org.spotify.model;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GenerationType;
import java.util.Objects;

@Entity
@NamedQuery(name = User.GET_ALL_USERS, query = "Select s from user s")
public class User {
    public static final String GET_ALL_USERS = "GetAllStudents";
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @SequenceGenerator(name = "student_seq", sequenceName = "student_seq", allocationSize = 1)

    public Long id;
    public String ime;
    public String prezime;

    public User() {

    }

    @Override
    public boolean equals(Object o) {
	if (o == null || getClass() != o.getClass()) return false;
	User user = (User) o;
	return Objects.equals(id, user.id) && Objects.equals(ime, user.ime) && Objects.equals(prezime, user.prezime);
    }

    @Override
    public int hashCode() {
	return Objects.hash(id, ime, prezime);
    }
}
