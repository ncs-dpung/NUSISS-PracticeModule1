package com.nusiss.inventory.backend.entity;

import com.nusiss.inventory.backend.enums.StockLevelEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "tbl_product")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Product {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    private BigDecimal price;

    @Column(name = "batch_no")
    private String batchNo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Column(name = "quantity_available")
    private int quantityAvailable;

    @Column(name = "reorder_level")
    private int reorderLevel;

    @Transient
    public String getStockLevel() {
        return quantityAvailable > reorderLevel ? StockLevelEnum.SUFFICIENT.name() : StockLevelEnum.LOW.name();
    }
}
