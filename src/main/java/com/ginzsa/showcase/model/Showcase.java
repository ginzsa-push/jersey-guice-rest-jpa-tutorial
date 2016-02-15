package com.ginzsa.showcase.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by santiago.ginzburg on 2/9/16.
 */
@XmlRootElement
@Entity
@Table( name = "showcase" )
public class Showcase implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="name")
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
