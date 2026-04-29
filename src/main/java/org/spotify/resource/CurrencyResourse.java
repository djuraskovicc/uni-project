package org.spotify.resource;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.spotify.client.EuroStateApi;
import org.spotify.model.CurrencyReponse;
import org.spotify.model.Users;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Path("/")
@EqualsAndHashCode
@NoArgsConstructor
public class CurrencyResourse {
    @Inject
    @RestClient
    EuroStateApi euroStateApi;

    @GET
    @RolesAllowed("admin")
    @Path("/currencyConversion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurrencyReponse(@QueryParam("from") String from,
				       @QueryParam("to") String to,
				       @QueryParam("value") double value,
				       @QueryParam("userId") int userId) {
	CurrencyReponse currencyReponse = euroStateApi.getCurrencyReponse(from, to, value, userId);
	double rate = currencyReponse.getRate();

	currencyReponse.setConvertedValue(value * rate);
	currencyReponse.setValue(value);

	return Response.ok().entity(currencyReponse).build();
    }
}
