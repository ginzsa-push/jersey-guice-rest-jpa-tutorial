package com.ginzsa.showcase.repo;

import com.ginzsa.showcase.model.Showcase;
import com.google.inject.Inject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by santiago.ginzburg on 2/9/16.
 */
public class MapDatasource implements BasicDatasource<Showcase> {
    private Map<String, Showcase> showcaseMap;

    @Inject
    public MapDatasource(Map<String, Showcase> showcaseMap) {
        this.showcaseMap = showcaseMap;
    }

    @Override
    public Map<String, Showcase> getShowcaseMap() {
        if (showcaseMap == null) {
            this.showcaseMap = new HashMap<String, Showcase>();
        }
        return showcaseMap;
    }

    @Override
    public void setShowcaseMap(Map<String, Showcase> showcaseMap) {
        this.showcaseMap = showcaseMap;
    }

    @Override
    public Showcase findIt(String id) {
        return this.getShowcaseMap().get(id);
    }
}
