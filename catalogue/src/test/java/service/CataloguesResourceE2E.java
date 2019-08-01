package service;

import client.CataloguesResourceClient;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import service.api.CataloguesResponse;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by dan on 01/08/19.
 */
@RunWith(Arquillian.class)
public class CataloguesResourceE2E {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addPackages(true, "service")
                .addAsResource("project-it.yml", "project-defaults.yml")
                .addAsResource("META-INF/persistence.xml")
                .addAsResource("META-INF/load.sql")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @ArquillianResource
    private URI uri;

    private CataloguesResourceClient client;

    @Before
    public void setUpClient() throws URISyntaxException {
        client = RestClientBuilder.newBuilder().baseUri(uri).register(JacksonJsonProvider.class).build(CataloguesResourceClient.class);
    }

    @Test
    @RunAsClient
    public void get_testCount() throws Exception {
        List<CataloguesResponse> catalogues = client.get();
        Assert.assertEquals(5, catalogues.size());
    }

}