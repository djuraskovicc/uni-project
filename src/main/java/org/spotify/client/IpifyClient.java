package org.spotify.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/ip")
@RegisterRestClient(configKey = "ipify-api")
public interface IpifyClient {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPublicIp();
}
