package com.example.manytoone2.repository;

import com.example.manytoone2.entity.Address;
import com.example.manytoone2.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: liujinhui
 * @Date: 2019/11/3 21:25
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
