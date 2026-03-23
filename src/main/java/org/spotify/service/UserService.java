package org.spotify.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.spotify.exception.UserException;
import org.spotify.model.Users;

import java.util.List;

@Dependent
public class UserService {
    @Inject
    private EntityManager em;

    @Transactional
    public Users createUser(Users user) throws UserException {
	if(user == null) {
	    throw new UserException("User is not forwarded!");
	}

	if(user.getEmail().isEmpty()) {
	    throw new UserException("Email is empty!");
	}

	if(user.getPasswordHash().isEmpty()) {
	    throw new UserException("Password is empty!");
	}

	return em.merge(user);
    }

    @Transactional
    public List<Users> getAllUsers() {
	List<Users> users = em.createNamedQuery(Users.GET_ALL_USERS, Users.class).getResultList();
	return users;
    }
}
