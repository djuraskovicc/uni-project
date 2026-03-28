package org.spotify.service;

import org.spotify.exception.SpotifyException;
import org.spotify.model.Playlist;
import org.spotify.model.Users;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.QueryParam;

@Dependent
public class PlaylistService {
    @Inject
    EntityManager em;

    @Transactional
    public Playlist createPlaylist(@QueryParam("id") Long id, Playlist playlist) throws SpotifyException {
	if(playlist == null) {
	    throw new SpotifyException("Playlist is not provided!");
	}

	if(playlist.name.isEmpty()) {
	    throw new SpotifyException("Playlist name is empty!");
	}
	
	Users owner = em.find(Users.class, id);
	playlist.setUser(owner);
	return em.merge(playlist);
    }
}
