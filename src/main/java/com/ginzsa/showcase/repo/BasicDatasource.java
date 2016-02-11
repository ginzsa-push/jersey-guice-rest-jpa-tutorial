package com.ginzsa.showcase.repo;

import java.util.Map;

/**
 * Created by santiago.ginzburg on 2/9/16.
 */
public interface BasicDatasource<T> {
    T findIt(String id);
    Map<String, T> getShowcaseMap();
    void setShowcaseMap(Map<String, T> showcaseMap);
}
