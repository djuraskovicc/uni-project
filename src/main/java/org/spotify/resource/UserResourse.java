package org.spotify.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.spotify.exception.UserException;
import org.spotify.model.Users;
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
	} catch (UserException e) {
	    return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
	}
	return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllUsers")
    public List<Users> getAllUsers() {
	return userService.getAllUsers();
    }
}
