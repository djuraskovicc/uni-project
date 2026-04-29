package org.spotify.scheduler;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.spotify.client.IpifyClient;
import org.spotify.client.TimeApi;
import org.spotify.model.Artist;
import org.spotify.model.TimeResponse;
import org.spotify.service.ArtistService;
import java.util.List;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ArtistScheduler {
    @Inject
    @RestClient
    IpifyClient ipifyClient;

    @Inject
    private ArtistService artistService;

    @Inject
    @RestClient
    TimeApi timeApi;

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

//    @Scheduled(every="5s")
//    public void increment(){
//	String myIp = ipifyClient.getPublicIp().trim();
//	TimeResponse timeResponse = timeApi.getTime(myIp);
//	System.out.println(timeResponse.getDate() + " " + timeResponse.getTime());
//    }
}
