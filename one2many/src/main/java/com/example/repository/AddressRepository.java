package com.example.repository;


import com.example.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: liujinhui
 * @Date: 2019/11/3 21:25
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
