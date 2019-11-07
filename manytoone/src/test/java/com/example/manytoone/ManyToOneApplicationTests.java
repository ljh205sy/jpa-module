package com.example.manytoone;

import com.example.manytoone.entity.Address;
import com.example.manytoone.entity.Person;
import com.example.manytoone.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ManyToOneApplicationTests {

    @Autowired
    private PersonRepository personRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testSavePerson() {
        Person person = new Person();
        person.setAddress(new Address(1, "武汉"));
        person.setGuid("1");
        person.setName("liujinhui");
        personRepository.save(person);
        person.setAddress(new Address(2, "孝感"));
    }

}
