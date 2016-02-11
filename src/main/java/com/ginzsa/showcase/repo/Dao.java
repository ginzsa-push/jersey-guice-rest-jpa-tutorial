package com.ginzsa.showcase.repo;

import java.util.List;

/**
 * Created by santiago.ginzburg on 2/9/16.
 */
public interface Dao<T> {

    List<T> getAll();
    T getById(Long id);
}
