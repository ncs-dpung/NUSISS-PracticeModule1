package com.nusiss.inventory.backend.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_role")
public class Role {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "roleid")
    private Long id;

    @Column(name = "role_name")
    private String name;
//
//    @OneToMany(mappedBy = "UserRole")
//    private List<User> users;

}
