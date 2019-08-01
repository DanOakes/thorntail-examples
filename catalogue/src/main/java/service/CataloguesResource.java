package service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import service.api.CataloguesResponse;
import service.api.ItemsResponse;
import service.domain.Catalogues;
import service.domain.Items;

@Path("/catalogues")
public class CataloguesResource {
	
    @Inject
    PersistenceHelper ph;

    @Inject
    @ConfigProperty(name = "application.validation")
    private String validation;

    @GET
    @Counted()
    @Produces(MediaType.APPLICATION_JSON)
    public List<CataloguesResponse> get() {
    	
    	EntityManager em = ph.getEntityManager();
    	TypedQuery<Catalogues> tq = em.createNamedQuery("Catalogues.findAll", Catalogues.class);
    	List<Catalogues> cats = tq.getResultList();
    	    	
    	List<CataloguesResponse> resp = new ArrayList<CataloguesResponse>(cats.size());
    	
    	for (int i = 0; i < cats.size(); i++) {
    		
    		CataloguesResponse cr = new CataloguesResponse();
    		cr.setId(cats.get(i).getId());
    		cr.setName(cats.get(i).getName());
    		cr.setType(cats.get(i).getType());
    		cr.setUrl(cats.get(i).getUrl());
    		cr.setCode(cats.get(i).getCode());
    		cr.setStatus(cats.get(i).getStatus());
    		
    		resp.add(cr);
    		
    	}
    	   	
        return resp;
   }
    
    @GET
    @Counted()
    @Timed(name = "timedCatalogueOptions")
    @Path("/{code}/items")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ItemsResponse> getOptions(@PathParam("code") String code) {
    	
    	if (code.startsWith(validation)) {
    		throw new WebApplicationException(404);
    	}
    	
    	EntityManager em = ph.getEntityManager();
    	TypedQuery<Items> tq = em.createNamedQuery("Items.findAll", Items.class).setParameter("catalogue", code);
    	List<Items> its = tq.getResultList();
    	
    	List<ItemsResponse> resp = new ArrayList<ItemsResponse>(its.size());
    	
    	for (int i = 0; i < its.size(); i++) {
    		
    		ItemsResponse ir = new ItemsResponse();
    		ir.setId(its.get(i).getId());
    		ir.setName(its.get(i).getName());
    		ir.setCode(its.get(i).getCode());
    		ir.setStatus(its.get(i).getStatus());
    		
    		resp.add(ir);
    		
    	}
    	    	    	
        return resp;
   }
}
