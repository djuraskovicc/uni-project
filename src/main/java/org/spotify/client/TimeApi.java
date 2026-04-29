package org.spotify.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.spotify.model.TimeResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@RegisterRestClient(configKey = "time-api")
public interface TimeApi {
    @GET
    @Path("/time/current/ip")
    public TimeResponse getTime(@QueryParam("ipAddress") String ipAddress);
}
