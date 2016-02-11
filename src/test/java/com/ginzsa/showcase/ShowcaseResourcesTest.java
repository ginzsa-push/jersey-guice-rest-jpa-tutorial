package com.ginzsa.showcase;

import com.ginzsa.showcase.model.Showcase;
import com.ginzsa.showcase.repo.BasicDatasource;
import com.ginzsa.showcase.repo.Dao;
import com.ginzsa.showcase.repo.MapDatasource;
import com.ginzsa.showcase.repo.ShowcaseDao;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.core.spi.component.ioc.IoCComponentProviderFactory;
import com.sun.jersey.guice.spi.container.GuiceComponentProviderFactory;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by santiago.ginzburg on 2/9/16.
 */
public class ShowcaseResourcesTest {

    static final URI BASE_URI = getBaseURI();
    private static HttpServer server;

    private Client client;
    private WebResource service;

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost/").port( 9998 ).build();
    }

    @Before
    public void startServer() throws IOException {
        server = checkServer();
        service = checkClient().resource( getBaseURI() );
    }

    private static HttpServer checkServer() throws IOException  {
        if (server == null || !server.isStarted()){
            Injector injector = Guice.createInjector(new ServletModule() {
                @Override
                protected void configureServlets() {
                    bind(BasicDatasource.class).to(MapDatasource.class);
                    bind(new TypeLiteral<Dao<Showcase>>() {
                    }).to(ShowcaseDao.class);
                }

                @Provides
                @Singleton
                protected Map<String, Showcase> showcaseMap() {
                    Showcase showcase = new Showcase(1L, "test showcase");
                    Map<String, Showcase> map = new HashMap<String, Showcase>();
                    map.put(showcase.getId().toString(), showcase);
                    return map;
                }
            });

            ResourceConfig rc = new PackagesResourceConfig( "com.ginzsa.showcase.resources" );

            rc.getFeatures().put("com.sun.jersey.api.json.POJOMappingFeature", true);

            IoCComponentProviderFactory ioc = new GuiceComponentProviderFactory( rc, injector );
            server = GrizzlyServerFactory.createHttpServer(BASE_URI + "services/", rc, ioc);
        }
        return server;
    }

    private Client checkClient() {

        if (client == null) {
            //client
            DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
            defaultClientConfig.getClasses().add(JacksonJsonProvider.class);

            client = Client.create(defaultClientConfig);
        }
        return client;
    }

    @Test
    public void testGetAll() throws IOException {

        ClientResponse resp = service.path( "services" ).path( "showcase" )
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        List<Showcase> list = resp.getEntity(new GenericType<List<Showcase>>() {});

        assertEquals( 200, resp.getStatus() );
        assertNotNull(list);
        assertFalse(list.isEmpty());
        Showcase showcase = list.get(0);
        assertNotNull(showcase);

        assertEquals( new Long(1), showcase.getId());
        assertEquals("test showcase", showcase.getShowCase());
    }

    @Test
    public void testGetById() {
        ClientResponse resp = service.path( "services" ).path( "showcase" ).path("1")
                .accept(MediaType.APPLICATION_JSON)
                .get( ClientResponse.class );
        Showcase showcase = resp.getEntity(Showcase.class);
        assertEquals( 200, resp.getStatus() );
        assertNotNull(showcase);
        assertEquals(new Long(1), showcase.getId());
        assertEquals("test showcase", showcase.getShowCase());
    }

    @After
    public void stopServer() {
        server.stop();
    }
}
