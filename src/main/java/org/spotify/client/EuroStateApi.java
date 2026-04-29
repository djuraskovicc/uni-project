package org.spotify.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.spotify.model.CurrencyReponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@RegisterRestClient(configKey = "euro-rates-api")
public interface EuroStateApi {
    @GET
    @Path("rates")
    public CurrencyReponse getCurrencyReponse(
					      @QueryParam("from") String from,
					      @QueryParam("to") String to,
					      @QueryParam("value") double value,
					      @QueryParam("userId") int userId);
}
