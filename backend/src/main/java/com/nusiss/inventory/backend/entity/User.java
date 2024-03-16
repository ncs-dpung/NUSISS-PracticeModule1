package com.nusiss.inventory.backend.entity;

import com.nusiss.inventory.backend.dto.UserDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Entity
@Table(name = "tbl_user")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@EqualsAndHashCode
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id")
  private Long id;

  @Column(name = "user_name", unique = true)
  private String username;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @OneToOne
  @JoinColumn(name = "staff_id")
  private Staff staff;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
      name = "user_role_junction",
      joinColumns = {@JoinColumn(name = "user_id")},
      inverseJoinColumns = {@JoinColumn(name = "role_id")})
  private Set<Role> roles;

  public User() {
    super();
    this.roles = new HashSet<Role>();
  }

  public User(String username, String password, String email) {
    super();
    this.username = username;
    this.password = password;
    this.email = email;
    this.roles = new HashSet<Role>();
  }

  public UserDto toDto() {
    UserDto dto = new UserDto();
    dto.setId(id);
    dto.setUsername(username);
    dto.setEmail(email);
    dto.setRoles(roles.stream().map(Role::toDto).collect(Collectors.toSet()));
    return dto;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public Collection<Role> getAuthorities() {
    return roles;
  }
}
