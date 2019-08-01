package service.manager;

import service.api.CataloguesResponse;
import service.domain.Catalogues;
import service.persistence.PersistenceHelper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dan on 02/08/19.
 */
@ApplicationScoped
public class CatalogueManagerImpl implements CatalogueManager{

    @Inject
    PersistenceHelper ph;

    @Override
    public List<CataloguesResponse> getAllCatalogues() {
        EntityManager em = ph.getEntityManager();
        TypedQuery<Catalogues> tq = em.createNamedQuery("Catalogues.findAll", Catalogues.class);
        List<Catalogues> cats = tq.getResultList();

        List<CataloguesResponse> cataloguesList = new ArrayList<CataloguesResponse>(cats.size());

        for (int i = 0; i < cats.size(); i++) {

            CataloguesResponse cr = new CataloguesResponse();
            cr.setId(cats.get(i).getId());
            cr.setName(cats.get(i).getName());
            cr.setType(cats.get(i).getType());
            cr.setUrl(cats.get(i).getUrl());
            cr.setCode(cats.get(i).getCode());
            cr.setStatus(cats.get(i).getStatus());

            cataloguesList.add(cr);

        }
        return cataloguesList;
    }
}
