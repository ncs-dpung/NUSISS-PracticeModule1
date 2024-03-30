package com.nusiss.inventory.backend.entity;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

  // TODO: Uncomment the following line when user management is implemented
  //    @ManyToOne
  //    @JoinColumn(name = "user_id")
  //    private User user;

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

  @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Collection<OrderItem> items;
}
