package org.spotify.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.spotify.model.User;
import org.spotify.service.UserService;

import java.util.List;

public class UserResourse {
    @Inject
    private UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addStudent")
    public String addUser(User user) {
      userService.createUser(user);
      return "Hello RESTEasy";
    }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/getAllStudents")
  public Response getAllStudents() {
    List<User> students = userService.getAllStudents();
    return Response.ok().entity(students).build();
  }
    
}
