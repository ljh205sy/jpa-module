package com.example.manytomany;

import com.example.manytomany.entity.Address;
import com.example.manytomany.entity.Person;
import com.example.manytomany.repository.AddressRepository;
import com.example.manytomany.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressTests {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void testSavePerson() {
        Set<Person> set = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            Person person = new Person("username" + i);
            set.add(person);
        }
        personRepository.saveAll(set);
    }

    @Test
    public void testSaveAddress() {
        Set<Address> set = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            Address address = new Address("address" + i);
            set.add(address);
        }
        addressRepository.saveAll(set);
    }

    @Test
    public void testFindAll() {
        List<Address> all = addressRepository.findAll();
        for (Address address : all) {
            System.out.println(address);
        }
    }

    // Address此处无法维护关系
    @Test
    public void testUpdateReference() {
        Address address = addressRepository.findById(1).get(); // address0
        Person person = personRepository.findById("4028f6d86e45a092016e45a0a5750004").get();  // username4
        address.getPersons().add(person);
        System.out.println(address);
        addressRepository.save(address);
    }

    // Person维护关系 -> 修改中间表
    @Test
    public void testUpdatePersonReference() {
        Person person = personRepository.findById("4028f6d86e45a092016e45a0a5750004").get();  // username4
        Address address = addressRepository.findById(2).get(); // address1
        Address address2 = addressRepository.findById(3).get(); // address2
        Set<Address> set = new HashSet<>();
        set.add(address);
        set.add(address2);
        person.setAddresss(set);
        System.out.println(person);
        personRepository.save(person);
    }

    // Person删除,同时删除中间表
    @Test
    public void testDeletePerson(){
        Person person = personRepository.findById("4028f6d86e45a092016e45a0a5750004").get();  // username4
        personRepository.delete(person);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testUpdatePerson() {
        Person person = personRepository.getOne("4028f6d86e45a092016e45a0a5750001");  // username1
        person.setName("xxxxxxxxx");
        System.out.println(person);
        personRepository.save(person);
    }


    @Test
    public void testupdateAddress() {
        Address address = addressRepository.getOne(3);
        System.out.println(address);
    }
}
