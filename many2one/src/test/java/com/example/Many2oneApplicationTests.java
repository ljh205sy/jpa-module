package com.example;

import com.example.entity.Address;
import com.example.entity.Person;
import com.example.repository.AddressRepository;
import com.example.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class Many2oneApplicationTests {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;


    @Test
    public void testPerSavePerson2() {
        /**
         * Hibernate: select address0_.addresszz as addressz1_0_0_, address0_.detail as detail2_0_0_ from address address0_ where address0_.addresszz=?
         * Hibernate: insert into address (detail) values (?)
         * Hibernate: select person0_.guid as guid1_1_0_, person0_.address_xxxx as address_3_1_0_, person0_.name as name2_1_0_ from person person0_ where person0_.guid=?
         * Hibernate: select address0_.addresszz as addressz1_0_0_, address0_.detail as detail2_0_0_ from address address0_ where address0_.addresszz=?
         * Hibernate: insert into person (address_xxxx, name, guid) values (?, ?, ?)
         */
        Address address = new Address(1, "武汉");   // 不设置级联操作
        addressRepository.save(address);
        Person person = new Person();
        person.setGuid("55555");
        person.setName("liujinhui11");
        person.setAddress(address);
        personRepository.save(person);
    }


    @Test
    public void testPerSavePerson() {
        /**
         * Hibernate: select person0_.guid as guid1_1_1_, person0_.address_xxxx as address_3_1_1_, person0_.name as name2_1_1_, address1_.addresszz as addressz1_0_0_, address1_.detail as detail2_0_0_ from person person0_ left outer join address address1_ on person0_.address_xxxx=address1_.addresszz where person0_.guid=?
         * Hibernate: select address0_.addresszz as addressz1_0_0_, address0_.detail as detail2_0_0_ from address address0_ where address0_.addresszz=?
         * Hibernate: insert into address (detail) values (?)
         * Hibernate: insert into person (address_xxxx, name, guid) values (?, ?, ?)
         */
        Person person = new Person();
        person.setGuid("55555");
        person.setName("liujinhui11");
        Address address = new Address(1, "武汉");  // 在person中设置cascade级联操作
        person.setAddress(address);
        personRepository.save(person);
    }


    /**
     * 更新和插入操作
     */
    @Test
    public void testupdatePerson() {
        Address address = new Address(1, "武汉");   // 不设置级联操作
        Person person = new Person();
        person.setGuid("4028f6d86e4152e3016e4152f04e0000");
        person.setName("1111");
        person.setAddress(address);
        personRepository.save(person);
    }

    @Test
    public void testdeletePerson() {
        Address address = new Address(1, "武汉");   // 不设置级联操作
        Person person = new Person();
        person.setGuid("4028f6d86e414fdf016e414fee4e0000");
        person.setAddress(address);
        personRepository.delete(person);
    }
}