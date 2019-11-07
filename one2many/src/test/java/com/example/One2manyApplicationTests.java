package com.example;

import com.example.entity.Address;
import com.example.entity.Person;
import com.example.repository.AddressRepository;
import com.example.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@RunWith(SpringRunner.class)
public class One2manyApplicationTests {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testPerSavePerson() {
        Person person = new Person();
        person.setGuid("55555");
        person.setName("liujinhui11");
        personRepository.save(person);
    }

    @Test
    public void testOne2Many() {
        /**
         *  先把主表的值插入，然后外键关联进行更新
         * Hibernate: select address0_.addresszz as addressz1_0_0_, address0_.detail as detail2_0_0_ from address address0_ where address0_.addresszz=?
         * Hibernate: insert into address (detail) values (?)
         * Hibernate: select person0_.guid as guid1_1_0_, person0_.name as name2_1_0_ from person person0_ where person0_.guid=?
         * Hibernate: update person set address_x=? where guid=?
         */
        Address address = new Address(1, "武汉");
        Person person = new Person();
        person.setGuid("222"); // guid如果不存在表person中就报错了，因为是update更新操作. 这个是Address没有设置cascade级联方式
        person.setName("liujinhui11");
        address.getPersons().add(person);
        addressRepository.save(address);
    }

    @Test
    public void testOne2ManyCascade() {
        /**
         * 先把主表的值插入，然后外键关联进行更新，如果不存在则插入，在更新。
         * Hibernate: select address0_.addresszz as addressz1_0_1_, address0_.detail as detail2_0_1_, persons1_.address_x as address_3_1_3_, persons1_.guid as guid1_1_3_, persons1_.guid as guid1_1_0_, persons1_.name as name2_1_0_ from address address0_ left outer join person persons1_ on address0_.addresszz=persons1_.address_x where address0_.addresszz=?
         * Hibernate: insert into address (detail) values (?)
         * Hibernate: select person0_.guid as guid1_1_0_, person0_.name as name2_1_0_ from person person0_ where person0_.guid=?
         * Hibernate: insert into person (name, guid) values (?, ?)
         * Hibernate: update person set address_x=? where guid=?
         */
        Address address = new Address(1, "武汉");
        Person person = new Person();
        person.setGuid("222"); // Address设置cascade级联，当不存在时会自动创建， 不会报错
        person.setName("liujinhui11");
        address.getPersons().add(person);
        addressRepository.save(address);
    }


    @Test
    public void testdeleteAddress() {
        Address address = new Address(1, "武汉");   // 不设置级联操作
        addressRepository.delete(address);
    }
}
