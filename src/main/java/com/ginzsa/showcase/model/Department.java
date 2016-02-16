package com.ginzsa.showcase.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by santiago.ginzburg on 2/4/16.
 */
@XmlRootElement
@Entity
@Table( name = "department" )
public class Department {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name="name")
    private String name;

    public Department() {
    }

    protected Department(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Builder {
        private Long id;
        private String name;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Department build() {
            return new Department(this);
        }
    }
}
