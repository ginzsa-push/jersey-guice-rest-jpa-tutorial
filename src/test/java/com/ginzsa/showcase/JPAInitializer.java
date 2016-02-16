package com.ginzsa.showcase;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.PersistService;

/**
 * Created by santiago.ginzburg on 2/16/16.
 */
@Singleton
public class JPAInitializer
{

    @Inject
    public JPAInitializer(final PersistService service)
    {
        service.start();
    }
}
