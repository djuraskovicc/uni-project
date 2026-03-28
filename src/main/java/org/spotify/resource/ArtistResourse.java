package org.spotify.resource;

import java.util.List;
import org.spotify.exception.SpotifyException;
import org.spotify.model.Album;
import org.spotify.model.Artist;
import org.spotify.service.ArtistService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/artists")
public class ArtistResourse {
    @Inject
    private ArtistService artistService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addArtist")
    public Response addArtist(Artist artist) {
	try {
	    artistService.createArtist(artist);
	} catch (SpotifyException e) {
	    return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
	}
	return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllArtists")
    public Response getAllArtists() {
	List<Artist> artists = artistService.getAllArtists();
	if (artists == null) {
	    return Response.status(Response.Status.NOT_FOUND).build();
	}
	return Response.ok().entity(artists).build();
    }

    @GET
    @Path("/getAlbumsByArtistId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlbumsByArtistId(@QueryParam("id") Long id) {
	List<Album> albums = artistService.getAlbumsByArtistId(id);
	if (albums == null) {
	    return Response.status(Response.Status.NOT_FOUND).build();
	}
	return Response.ok().entity(albums).build();
    }
}
