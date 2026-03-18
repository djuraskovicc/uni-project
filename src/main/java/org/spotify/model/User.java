package org.spotify.model;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import java.util.Objects;

@Entity
@Table(name = "users")
@NamedQuery(name = User.GET_ALL_USERS, query = "Select s from User s")
public class User {
    public static final String GET_ALL_USERS = "GetAllUsers";
   
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)

    public Long id;
    public String email;
    public String password_hash;
    public String created_at;

    public User() {

    }

    @Override
    public boolean equals(Object o) {
	if (o == null || getClass() != o.getClass()) return false;
	User user = (User) o;
	return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
	return Objects.hash(id, email);
    }
}
