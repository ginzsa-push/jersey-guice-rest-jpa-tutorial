package com.ginzsa.showcase.repo;

import com.ginzsa.showcase.model.Showcase;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by santiago.ginzburg on 2/9/16.
 */
public class ShowcaseDao implements Dao<Showcase> {

    private MapDatasource mapDatasource;

    @Inject
    public ShowcaseDao(MapDatasource mapDatasource) {
        this.mapDatasource = mapDatasource;
    }

    @Override
    public List<Showcase> getAll() {
        //return new ArrayList<Showcase>(this.mapDatasource.getShowcaseMap().values());
        List<Showcase> list = this.mapDatasource.getShowcaseMap()
                .values().stream().collect(Collectors.toList());
        return list;
    }

    @Override
    public Showcase getById(Long id) {
        return this.mapDatasource.getShowcaseMap().get(id.toString());
    }
}