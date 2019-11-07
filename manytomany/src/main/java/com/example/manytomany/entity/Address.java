package com.example.manytomany.entity;

import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: liujinhui
 * @Date: 2019/11/3 20:33
 */
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addresszz;
    @Column
    private String detail;

    // addresss是Person实体中的属性。 此实体中不维护关系，中间表中的数据由Person维护，例如删除，修改等，address实体没有无法操作
    // mapppedBy 与 JoinColumn或者JoinTable互斥。 addresss是对方的实体属性值
    @ManyToMany(mappedBy = "addresss",fetch = FetchType.EAGER)
    private Set<Person> persons = new HashSet<>();

    public Address(String detail) {
        this.detail = detail;
    }

    public Address(){}

    public Integer getAddresszz() {
        return addresszz;
    }

    public void setAddresszz(Integer addresszz) {
        this.addresszz = addresszz;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addresszz=" + addresszz +
                ", detail='" + detail + '\'' +
                ", persons=" + persons +
                '}';
    }
}
