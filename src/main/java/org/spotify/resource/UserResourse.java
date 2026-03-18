package org.spotify.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.spotify.model.User;
import org.spotify.service.UserService;

import java.util.List;

@Path("/user")
public class UserResourse {
    @Inject
    private UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addUser")
    public String addUser(User user) {
	userService.createUser(user);
	return "Hello RESTEasy";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllUsers")
    public Response getAllUsers() {
	List<User> users = userService.getAllUsers();
	return Response.ok().entity(users).build();
    }    
}
