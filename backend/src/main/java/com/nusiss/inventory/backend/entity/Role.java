package com.nusiss.inventory.backend.entity;

import com.nusiss.inventory.backend.dto.RoleDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Table(name = "tbl_role")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Role extends BaseAuditEntity implements GrantedAuthority {

  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "role_id")
  private Long id;

  @Column(name = "role_name")
  private String name;

  @EqualsAndHashCode.Exclude
  @ManyToMany(mappedBy = "roles")
  private Set<User> users;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
      name = "role_action_junction",
      joinColumns = {@JoinColumn(name = "role_id")},
      inverseJoinColumns = {@JoinColumn(name = "action_id")})
  private Set<AuthorisedAction> actions;

  public Role(String name) {
    this.name = name;
    this.actions = new HashSet<>();
    AuthorisedAction baseAction = new AuthorisedAction(name);
    actions.add(baseAction);
  }

  @Override
  public String getAuthority() {
    String scope =
        actions.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
    return scope;
  }

  public RoleDto toDto() {
    RoleDto dto = new RoleDto();
    dto.setId(id);
    dto.setName(name);
    dto.setActions(actions.stream().map(AuthorisedAction::toDto).collect(Collectors.toSet()));
    return dto;
  }
}
