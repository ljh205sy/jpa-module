package com.example.manytomany;

import com.example.manytomany.entity.Role;
import com.example.manytomany.entity.User;
import com.example.manytomany.repository.RoleRepository;
import com.example.manytomany.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRoleTests {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;


    /**
     * 新增用户、角色数据
     */
    @Test
    public void testSaveRoleAndUser() {
        User user = new User(2, "username2");
        Role role = new Role();
        role.setRoleName("role1");
        role.getUsers().add(user);
        roleRepository.save(role);
    }




    /**
     * 新增用户、角色数据
     */
    @Test
    public void testSaveRole2() {
        Role role = new Role();
        role.setRole_id(2);
        role.setRoleName("role2");
        roleRepository.save(role);
    }

    @Test
    public void testFindRole2() {
        Optional<Role> byId = roleRepository.findById(3);
        byId.ifPresent(role -> {
            System.out.println(role);
            Set<User> users = role.getUsers();
            if (!users.isEmpty()) {
                Iterator<User> iterator = users.iterator();
                while (iterator.hasNext()) {
                    User user = iterator.next();
                    System.out.println(user);
                }
            }
        });
        System.out.println("---------------------");
    }


    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUser_id(3);
        user.setUserName("username3");
        Optional<Role> byId = roleRepository.findById(3);
        user.getRoles().add(byId.get());
        userRepository.save(user);
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setUser_id(3);
        user.setUserName("username3");
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(4, "role2"));
        user.setRoles(roles);
        userRepository.save(user);
    }
}
