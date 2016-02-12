package com.ginzsa.showcase.config;

import com.ginzsa.showcase.model.Showcase;
import com.ginzsa.showcase.repo.*;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by santiago.ginzburg on 2/9/16.
 */
public class Main extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {

                bind(ShowcaseDao.class).to(ShowcaseImplDao.class);

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
            public EntityManagerFactory entityManagerFactory() {
                return Persistence.createEntityManagerFactory("testDB");
            }

            @Provides
            @Singleton
            public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
                return entityManagerFactory.createEntityManager();
            }
        });
    }
}
