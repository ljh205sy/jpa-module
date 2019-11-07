package com.example.manytoone.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Author: liujinhui
 * @Date: 2019/11/3 20:33
 */
@Data
@Entity
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;
    @Column
    private String detail;

    public Address(Integer addressId, String detail) {
        this.addressId = addressId;
        this.detail = detail;
    }
}
