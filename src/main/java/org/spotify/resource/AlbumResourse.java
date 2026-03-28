package org.spotify.resource;

import org.spotify.exception.SpotifyException;
import org.spotify.model.Album;
import org.spotify.service.AlbumService;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
public class AlbumResourse {
    @Inject
    AlbumService albumService;

    @POST
    @Path("/addAlbum")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAlbum(@QueryParam("id") Long id, Album album) {
	try {
	    albumService.createAlbum(id, album);
	    return Response.ok().build();	
	} catch (SpotifyException e) {
	    return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
	}
    }
}
