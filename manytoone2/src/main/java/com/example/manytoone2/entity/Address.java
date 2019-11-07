package com.example.manytoone2.entity;

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
@Data
@Entity()
@NoArgsConstructor
@Table(name = "t_address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addresszz;
    @Column
    private String detail;

    /**
     * 双向一对多映射
     */
//            由addres来负责维护依赖关系，  一方 @OneToMany(mappedBy = "one")
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "address", cascade = {CascadeType.REFRESH, CascadeType.REFRESH})
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "address", cascade = {CascadeType.ALL})
    private Set<Person> persons = new HashSet<>();


    public Address(Integer addresszz) {
        this.addresszz = addresszz;
    }

    public Address(Integer addresszz, String detail) {
        this.addresszz = addresszz;
        this.detail = detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(getAddresszz(), address.getAddresszz());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAddresszz());
    }
}
