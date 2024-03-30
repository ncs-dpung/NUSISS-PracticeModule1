package com.nusiss.inventory.backend.service;

import com.nusiss.inventory.backend.dto.ProductDto;
import com.nusiss.inventory.backend.entity.Product;
import java.util.List;

public interface ProductService {
  Product createProduct(ProductDto productDto);

  Product updateProduct(Long productId, ProductDto productDto);

  void deleteProduct(Long productId);

  List<ProductDto> findAllProducts();

  ProductDto convertToDTO(Product product);
}
