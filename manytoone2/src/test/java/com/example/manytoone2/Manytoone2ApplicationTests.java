package com.example.manytoone2;

import com.example.manytoone2.entity.Address;
import com.example.manytoone2.entity.Person;
import com.example.manytoone2.repository.AddressRepository;
import com.example.manytoone2.repository.PersonRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;


@SpringBootTest
@RunWith(SpringRunner.class)
public class Manytoone2ApplicationTests {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testPerSavePerson() {
        Address address = new Address(1, "武汉"); //  如果不存在address为1的数据，屏蔽就报错,要先插入主表数据
        Person person = new Person();
        person.setGuid("55555");
        person.setAddress(address);
        person.setName("liujinhui11");
        person.setAddress(address);
        personRepository.save(person);
        person.setAddress(new Address(2, "孝感"));
    }


    @Test
    @Transactional
    @Rollback(false)
    /**
     *  保存地址，cascade级联后会自动保存person信息，由1的一方维护数据
     */
    public void testPerSaveAddress() {
        Address address = new Address(1, "武汉");
        Person person = new Person();
        person.setGuid("4028f6d86e3f8fd0016e3f8fe9830000");
        person.setAddress(address);
        person.setName("liujinhui");
        address.getPersons().add(person);
        addressRepository.save(address);
    }


    @Test
    // 删除不了数据， 测试用例是通过的。
    public void testDeletePerson() {
        Address address = new Address(1);
        Person person = new Person();
        person.setGuid("4028f6d86e404fb3016e404fd0470000");
        person.setAddress(address);
        person.setName("liujinhui");
        System.out.println("---------------------------");
        personRepository.delete(person);
    }


    @Test
    public void testDeleteAddress() {
        Address address = addressRepository.getOne(1);
        System.out.println("---------------------------");
        addressRepository.delete(address);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testSavePerson() {
        Address address = new Address(2, "武汉");
        // 如果没有cascade操作，不会级联保存，那么保存person的时候就会存在外键约束，提示报错。没有保存Address
        Person person = new Person();
        person.setAddress(address);
        person.setGuid("1");
        person.setName("liujinhui");
        personRepository.save(person);
        person.setAddress(new Address(2, "孝感"));
    }


    /**
     * @return
     */
    @Test
    public void testFindById() {
        Optional<Address> optional = addressRepository.findById(1);
        Address address = optional.get();
        Assert.assertEquals("武汉", address.getDetail());
    }

    /**
     * fetch属性是该实体的加载方式，有两种：LAZY和EAGER。默认为惰性加载，一般也建议使用惰性加载。
     * 多的加载一的一方，不用配置fetch，不会报错
     */
    @Test
    public void testFindAddressByPersonId() {
        Optional<Person> optional = personRepository.findById("4028f6d86e3f8fd0016e3f8fe9830000");
        Person person = optional.get();
        System.out.println(person.getAddress().getDetail());
        Assert.assertEquals("武汉", person.getAddress().getDetail());
    }

    /**
     * 一的一方加载多的一放， 那么fetch就要报错，需要增加fetch属性。
     * 因为是一的一方需要加载，所以需要一的一方进行关联， 设置@OneToMany(fetch = FetchType.EAGER)
     * 如果存在中间表，则会报错
     */
    @Test
    public void testFindPersonByAddress() {
        Optional<Address> optional = addressRepository.findById(1);
        Address address = optional.get();
        Assert.assertEquals("武汉", address.getDetail());
        Iterator<Person> iterator = address.getPersons().iterator();
        while (iterator.hasNext()) {
            Person person = iterator.next();
            System.out.println(person);
        }
    }
}
