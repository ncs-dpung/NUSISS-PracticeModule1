package com.nusiss.inventory.backend.entity;

import com.nusiss.inventory.backend.dto.AuthorisedActionDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "tbl_action")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorisedAction implements GrantedAuthority {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "action_id")
  private Long id;

  private String authority;

  public AuthorisedAction(String authority) {
    this.authority = authority;
  }

  public AuthorisedActionDto toDto() {
    AuthorisedActionDto dto = new AuthorisedActionDto();
    dto.setId(id);
    dto.setAuthority(authority);
    return dto;
  }
}
