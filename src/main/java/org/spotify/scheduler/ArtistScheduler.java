package org.spotify.scheduler;

import org.spotify.model.Artist;
import org.spotify.service.ArtistService;
import java.util.List;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ArtistScheduler {
    @Inject
    private ArtistService artistService;

    @Scheduled(cron = "0 0 2 * * ?")
    @Transactional
    public void refresh() {
	List<Artist> artists = artistService.getAllArtists();
	
	for (Artist artist : artists) {
	    if (artist.isNew) {
		System.out.println(artist.name + " has new release.");
	    }
	}
    }
}
