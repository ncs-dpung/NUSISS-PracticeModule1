package com.nusiss.inventory.backend.dto;

import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {
  private Long id;
  private String name;
  private Long categoryId;
  private String categoryName;
  private BigDecimal price;
  private String batchNo;
  private Long supplierId;
  private String supplierName;
  private int quantityAvailable;
  private int reorderLevel;
  private String stockLevel;

  //    public Product toEntity(Category category, Supplier supplier) {
  //        Product product = new Product();
  //        product.setId(this.id);
  //        product.setName(this.name);
  //        //Category category = new Category();
  //        category.setCategoryId(this.categoryId);
  //        product.setCategory(category);
  //
  //        //Supplier supplier = new Supplier();
  //        supplier.setId(this.supplierId);
  //        product.setSupplier(supplier);
  //
  //        product.setPrice(this.price);
  //        product.setBatchNo(this.batchNo);
  //        product.setQuantityAvailable(this.quantityAvailable);
  //        product.setReorderLevel(this.reorderLevel);
  //        // Note: Stock level is derived and not set directly
  //
  //        return product;
  //    }
}
