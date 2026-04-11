package org.spotify.client;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/api/admin")
public class RolesResource {
    @GET
    @RolesAllowed("admin")
    public String getAdminData() {
        return "Ovo vidi samo admin!";
    }
}
