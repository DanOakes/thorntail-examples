package client;

import service.api.CataloguesResponse;
import service.api.ItemsResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/catalogues")
public interface CataloguesResourceClient {
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<CataloguesResponse> get();
    
    @GET
    @Path("/{code}/items")
    @Produces(MediaType.APPLICATION_JSON)
    List<ItemsResponse> getOptions(@PathParam("code") String code);
}
