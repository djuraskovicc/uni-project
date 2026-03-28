package org.spotify.service;

import org.spotify.exception.SpotifyException;
import org.spotify.model.Album;
import org.spotify.model.Artist;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.QueryParam;

@Dependent
public class AlbumService {
    @Inject
    private EntityManager em;

    @Transactional
    public Album createAlbum(@QueryParam("id") Long id, Album album) throws SpotifyException {
	if(album == null) {
	    throw new SpotifyException("Album is not provided!");
	}

	if(album.title.isEmpty()) {
	    throw new SpotifyException("Album title is empty!");
	}
	
	Artist owner = em.find(Artist.class, id);
	album.setArtist(owner);
	return em.merge(album);
    }
}
