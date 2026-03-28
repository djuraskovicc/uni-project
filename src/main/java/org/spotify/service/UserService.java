package org.spotify.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.spotify.exception.SpotifyException;
import org.spotify.model.Playlist;
import org.spotify.model.Users;
import org.spotify.model.Profiles;

import java.util.List;

@Dependent
public class UserService {
    @Inject
    private EntityManager em;

    @Transactional
    public Users createUser(Users user) throws SpotifyException {
	if(user == null) {
	    throw new SpotifyException("User is not provided!");
	}

	if(user.email.isEmpty()) {
	    throw new SpotifyException("Email is empty!");
	}

	if(user.passwordHash.isEmpty()) {
	    throw new SpotifyException("Password is empty!");
	}

	return em.merge(user);
    }

    @Transactional
    public List<Users> getAllUsers() {
	return em.createNamedQuery(Users.GET_ALL_USERS, Users.class).getResultList();
    }

    public List<Playlist> getPlaylistsByUserId(Long id) {
	return em.createNamedQuery(Playlist.GET_ALL_PLAYLISTS_FOR_USER, Playlist.class)
	    .setParameter("id", id).getResultList();
    }

    public Profiles getUserProfile(Long id) {
	return em.find(Profiles.class, id);
    }
}
