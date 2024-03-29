package com.nusiss.inventory.backend.controllers;

import com.nusiss.inventory.backend.dto.ProductDto;
import com.nusiss.inventory.backend.entity.Product;
import com.nusiss.inventory.backend.service.OrderService;
import com.nusiss.inventory.backend.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Api(value = "Product Management System")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "View a list of available products", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved product list")
    })
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.findAllProducts();
        return ResponseEntity.ok(products);
    }

    @ApiOperation(value = "Add a product")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully add product")
    })
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        Product product = productService.createProduct(productDto);
        return ResponseEntity.ok(productService.convertToDTO(product));
    }

    @ApiOperation(value = "Update a product")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted product"),
            @ApiResponse(code = 404, message = "Product not found"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Product updatedProduct = productService.updateProduct(id, productDto);
        return ResponseEntity.ok(productService.convertToDTO(updatedProduct));
    }

    @ApiOperation(value = "Delete a product")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted product"),
            @ApiResponse(code = 500, message = "Cannot delete product with quantity more than 0.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}

