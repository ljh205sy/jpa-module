package com.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Author: liujinhui
 * @Date: 2019/11/3 20:33
 */
@Entity
@NoArgsConstructor
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addresszz;
    @Column
    private String detail;

    // Set<Person> persons 定义集合，name指定外键列,对应当前表的主键的列
    // 在student表中建立一个字段address_x, 用来映射当前表的主键列
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_x")
    private Set<Person> persons = new HashSet<>();

    public Address(Integer addresszz, String detail) {
        this.addresszz = addresszz;
        this.detail = detail;
    }

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
}
