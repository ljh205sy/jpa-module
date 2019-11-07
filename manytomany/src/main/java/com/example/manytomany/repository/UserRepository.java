package com.example.manytomany.repository;


import com.example.manytomany.entity.Address;
import com.example.manytomany.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: liujinhui
 * @Date: 2019/11/3 21:25
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
