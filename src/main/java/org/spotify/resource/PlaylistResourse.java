package org.spotify.resource;

import org.spotify.exception.SpotifyException;
import org.spotify.model.Playlist;
import org.spotify.service.PlaylistService;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
public class PlaylistResourse {
    @Inject
    PlaylistService playlistService;

    @POST
    @Path("/addPlaylist")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("id") Long id, Playlist playlist) {
	try {
	    playlistService.createPlaylist(id, playlist);
	    return Response.ok().build();	
	} catch (SpotifyException e) {
	    return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
	}
    }
}
