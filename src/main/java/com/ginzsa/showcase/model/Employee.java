package com.ginzsa.showcase.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by santiago.ginzburg on 2/2/16.
 */
@XmlRootElement
@Entity
@Table( name = "employee" )
public class Employee {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name="firstname")
    private String firstName;
    @Column(name="surname")
    private String lastName;
    @OneToOne
    @JoinColumn(name="id", nullable=false)
    private Department department;

    public Employee() {
    }

    protected Employee(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.department = builder.department;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Department getDepartment() { return department; }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private Department department;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withDepartment(Department department) {
            this.department = department;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }
}
