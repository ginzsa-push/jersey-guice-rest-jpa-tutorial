package com.ginzsa.showcase.config;

import com.ginzsa.showcase.model.Showcase;
import com.ginzsa.showcase.repo.BasicDatasource;
import com.ginzsa.showcase.repo.Dao;
import com.ginzsa.showcase.repo.MapDatasource;
import com.ginzsa.showcase.repo.ShowcaseDao;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import org.codehaus.jackson.map.DeserializationConfig;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by santiago.ginzburg on 2/9/16.
 */
public class Main extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {

                bind(BasicDatasource.class).to(MapDatasource.class);
                bind(new TypeLiteral<Dao<Showcase>>() {}).to(ShowcaseDao.class);

                ResourceConfig rc = new PackagesResourceConfig( "com.ginzsa.showcase.resources" );

                for ( Class<?> resource : rc.getClasses() ) {
                    bind( resource );
                }

                Map<String, String> initParams = new HashMap<String, String>();
                initParams.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
                serve( "/services/*" ).with(GuiceContainer.class, initParams);
            }

            @Provides
            @Singleton
            protected Map<String, Showcase> showcaseMap() {
                Showcase showcase = new Showcase(1L, "test showcase");
                Showcase showcase2 = new Showcase(2L, "test showcase 2");
                Map<String, Showcase> map = new HashMap<String, Showcase>();
                map.put(showcase.getId().toString(), showcase);
                map.put(showcase2.getId().toString(), showcase2);
                return map;
            }
        });
    }
}
