package org.spotify.service;

import java.util.List;
import org.spotify.exception.SpotifyException;
import org.spotify.model.Album;
import org.spotify.model.Artist;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Dependent
public class ArtistService {
    @Inject
    private EntityManager em;

    @Transactional
    public Artist createArtist(Artist artist) throws SpotifyException {
	if(artist == null) {
	    throw new SpotifyException("Artist is not provided!");
	}

	if(artist.name.isEmpty()) {
	    throw new SpotifyException("Name is empty!");
	}

	if(artist.genre.isEmpty()) {
	    throw new SpotifyException("Genre is empty!");
	}

	return em.merge(artist);
    }

    @Transactional
    public List<Artist> getAllArtists() {
	return em.createNamedQuery(Artist.GET_ALL_ARTISTS, Artist.class).getResultList();
    }

    public List<Album> getAlbumsByArtistId(Long id) {
	return em.createNamedQuery(Album.GET_ALL_ALBUMS_FOR_ARTIST, Album.class)
	    .setParameter("id", id).getResultList();
    }    
}
