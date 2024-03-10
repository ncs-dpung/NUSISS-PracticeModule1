package com.nusiss.inventory.backend.entity;

import com.nusiss.inventory.backend.dto.StaffDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name = "tbl_staff")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@EqualsAndHashCode
public class Staff {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "staff_id")
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private User user;

  public Staff() {
    super();
    this.user = new User();
  }

  public StaffDto toDto() {
    StaffDto dto = new StaffDto();
    dto.setId(id);
    dto.setFirstName(firstName);
    dto.setLastName(lastName);
    dto.setUser(user.toDto());
    return dto;
  }
}
