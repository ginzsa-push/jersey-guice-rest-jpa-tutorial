package com.ginzsa.showcase.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by santiago.ginzburg on 2/9/16.
 */
@XmlRootElement
public class Showcase {

    private Long id;
    private String showCase;

    public Showcase() {
    }

    public Showcase(final Long id, final String showCase) {
        this.showCase = showCase;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShowCase() {
        return showCase;
    }

    public void setShowCase(String showCase) {
        this.showCase = showCase;
    }
}
