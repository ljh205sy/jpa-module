package com.example.manytomany.repository;

import com.example.manytomany.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: liujinhui
 * @Date: 2019/11/3 21:25
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, String> {

}
