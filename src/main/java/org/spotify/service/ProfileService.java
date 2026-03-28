package org.spotify.service;

import org.spotify.exception.SpotifyException;
import org.spotify.model.Users;
import org.spotify.model.Profiles;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.QueryParam;

@Dependent
public class ProfileService {
    @Inject
    private EntityManager em;

    @Transactional
    public Profiles createProfile(@QueryParam("id") Long id, Profiles profile) throws SpotifyException {
	if(profile == null) {
	    throw new SpotifyException("Profile is not provided!");
	}

	if(profile.displayName.isEmpty()) {
	    throw new SpotifyException("Profile display name is empty!");
	}

	Users owner = em.find(Users.class, id);
	profile.setUser(owner);
        return em.merge(profile);
    }
}
