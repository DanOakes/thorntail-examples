package service.persistence;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import service.domain.Catalogues;
import service.persistence.PersistenceHelper;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by dan on 01/08/19.
 */
@RunWith(Arquillian.class)
public class PersistenceHelperIT {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addPackages(true, "service")
                .addAsResource("project-it.yml", "project-defaults.yml")
                .addAsResource("META-INF/persistence.xml")
                .addAsResource("META-INF/load.sql")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    PersistenceHelper ph;

    @Test
    public void findAllCatalogues_allResults() throws Exception {

        EntityManager em = ph.getEntityManager();
        TypedQuery<Catalogues> tq = em.createNamedQuery("Catalogues.findAll", Catalogues.class);
        List<Catalogues> cats = tq.getResultList();
        Assert.assertEquals(5, cats.size());
    }


}