package com.example.manytoone.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Author: liujinhui
 * @Date: 2019/11/3 20:28
 * N个person对应一个地址
 * 一个地址住这多个Person，单方面体现, 无连接表的单向映射
 * JPA使用@OneToMany和@ManyToOne来标识一对多的双向关联。
 * 一端(Address)使用@OneToMany,多端(Person)使用@ManyToOne。
 * <p>
 * 在JPA规范中，一对多的双向关系由多端(Person)来维护。
 * 就是说多端(Person)为关系维护端，负责关系的增删改查。一端(Address)则为关系被维护端，不能维护关系。
 * <p>
 * 一端(Address)使用@OneToMany注释的mappedBy="address"属性表明Address是关系被维护端。
 * 多端(Person)使用@ManyToOne和@JoinColumn来注释属性 author
 * ,@ManyToOne表明Person是多端，@JoinColumn设置在Address表中的关联字段(外键)。
 */
@Data
@Entity(name = "person")
public class Person {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String guid;
    @Column
    private String name;
    // 可选属性optional=false,表示address不能为空, 删除用户，不影响地址。 如果是CascadeType.ALL可以试试，删除person可能会影响到地址的删除
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    // 设置在Address表中关联字段（外键）
    @JoinColumn(name = "addressId")
    private Address address;
}
