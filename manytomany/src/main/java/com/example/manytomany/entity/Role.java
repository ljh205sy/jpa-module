package com.example.manytomany.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: liujinhui
 * @Date: 2019/11/3 20:33
 */
@Entity
@NoArgsConstructor
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer role_id;
    @Column
    private String roleName;

    //    @ManyToMany(fetch = FetchType.EAGER)
    @ManyToMany(mappedBy = "roles")
    // 由
    private Set<User> users = new HashSet<>();

    public Role(Integer role_id, String roleName) {
        this.role_id = role_id;
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + role_id +
                ", roleName='" + roleName + '\'' +
//                ", users=" + users +
                '}';
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public String getRoleName() {
        return roleName;
    }

    public Set<User> getUsers() {
        return users;
    }
}
