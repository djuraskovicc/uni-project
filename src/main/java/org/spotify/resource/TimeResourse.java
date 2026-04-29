package org.spotify.resource;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.spotify.client.IpifyClient;
import org.spotify.client.TimeApi;
import org.spotify.model.TimeResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/time")
public class TimeResourse {
    @Inject
    @RestClient
    IpifyClient ipifyClient;

    @Inject
    @RestClient
    TimeApi timeApi;

    @GET
    public Response getTime() {
	String myPublicIp = ipifyClient.getPublicIp();
        TimeResponse timeResponse = timeApi.getTime(myPublicIp);

	return Response.ok().entity(timeResponse).build();
    }
}
