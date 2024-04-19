package com.nusiss.inventory.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

@Table(name = "tbl_order")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long orderId;

  @ManyToOne
  @JoinColumn(name = "staff_id")
  private Staff staff;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @Column(name = "date_placed")
  private Date datePlaced;

  @Column(name = "date_shipped")
  private Date dateShipped;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "order_status_id")
  private OrderStatus status;

  @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  private Collection<OrderItem> items;
}
