package org.spotify.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.spotify.exception.SpotifyException;
import org.spotify.model.Playlist;
import org.spotify.model.Users;
import org.spotify.model.Profiles;
import org.spotify.service.UserService;
import java.util.List;

@Path("/users")
public class UserResourse {
    @Inject
    private UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addUser")
    public Response addUser(Users user) {
	try {
	    userService.createUser(user);
	} catch (SpotifyException e) {
	    return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
	}
	return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllUsers")
    public Response getAllUsers() {
	List<Users> users = userService.getAllUsers();
	if (users == null) {
	    return Response.status(Response.Status.NOT_FOUND).build();
	}
	return Response.ok().entity(users).build();
    }

    @GET
    @Path("/getPlaylistsByUserId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylistsByUserId(@QueryParam("id") Long id) {
	List<Playlist> playlists = userService.getPlaylistsByUserId(id);
	if (playlists == null) {
	    return Response.status(Response.Status.NOT_FOUND).build();
	}
	return Response.ok().entity(playlists).build();
    }

    @GET
    @Path("/getUserProfile")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserProfile(@QueryParam("id") Long id) {
	Profiles profile = userService.getUserProfile(id);
	return Response.ok().entity(profile).build();
    }
}
