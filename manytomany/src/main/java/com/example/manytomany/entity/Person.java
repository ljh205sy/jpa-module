package com.example.manytomany.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Author: liujinhui
 * @Date: 2019/11/3 20:28
 * <p>
 * 多对多， 多个person对用多个address
 */
@Entity
    @Table(name = "person")
    public class Person {
        @Id
        @GeneratedValue(generator = "uuid")
        @GenericGenerator(name = "uuid", strategy = "uuid")
        private String guid;
        @Column
        private String name;

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "person_address", joinColumns = @JoinColumn(name = "person_id1"),
                inverseJoinColumns = @JoinColumn(name = "address_id2"))
        private Set<Address> addresss = new HashSet<>();

    @Override
    public String toString() {
        return "Person{" +
                "guid='" + guid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(){}

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Address> getAddresss() {
        return addresss;
    }

    public void setAddresss(Set<Address> addresss) {
        this.addresss = addresss;
    }

}
