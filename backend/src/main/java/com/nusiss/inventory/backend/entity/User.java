package com.nusiss.inventory.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_user")
@EntityListeners(AuditingEntityListener.class)
public class User {
  @Id
  @Column(name = "userid")
  private Long id;

  @Column(name = "user_name")
  private String username;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "roleiD")
  private Role role;

  @Column(updatable = false, nullable = false)
  @CreatedDate
  private LocalDateTime createdTime;

  @Column(nullable = false)
  @LastModifiedDate
  private LocalDateTime updatedTime;
}
