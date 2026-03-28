package org.spotify.resource;

import org.spotify.exception.SpotifyException;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.spotify.model.Profiles;
import org.spotify.service.ProfileService;

@Path("/")
public class ProfileResourse {
    @Inject
    private ProfileService ps;

    @POST
    @Path("/addProfile")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProfile(@QueryParam("id") Long id, Profiles profile) {
	try {
	    ps.createProfile(id, profile);
	    return Response.ok().build();	
	} catch (SpotifyException e) {
	    return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
	}
    }
}
