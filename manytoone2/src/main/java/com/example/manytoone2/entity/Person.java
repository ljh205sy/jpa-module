package com.example.manytoone2.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

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
@Entity
@Table(name = "t_person")
public class Person {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String guid;
    @Column
    private String name;
    /**
     * 由addres来负责维护依赖关系
     * address_person 指定第三张表，指定外键列， @JoinColumn(name="address_id")指的是下面属性对应第三张表的列
     * inverseJoinColumns 是指维护关系的那一方对应的列
     */


    @ManyToOne(cascade = CascadeType.REMOVE)
    // 第三方表中的列，数据插入是反家伙
//    @JoinTable(name="address_person", joinColumns = @JoinColumn(name="address_id"), inverseJoinColumns = @JoinColumn(name="person_id"))

    // 第三方表中的列，数据插入是正确的
    @JoinTable(name = "address_person", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Address address;

    //    备注：JoinTable用于维护中间表，name中间表表名，两个外键： joinColumns=指向自己的外键 inverseJoinColumns=指向对方的外键
//    @ManyToMany
//    @JoinTable(name="中间表名"
//            ,joinColumns={@JoinColumn(name="自己的外键")}
//            ,inverseJoinColumns={@JoinColumn(name="对方的外键")})
//    private Set many1=new HashSet<Many1>();


    @Override
    public String toString() {
        return "Person{" +
                "guid='" + guid + '\'' +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(getGuid(), person.getGuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGuid());
    }
}
