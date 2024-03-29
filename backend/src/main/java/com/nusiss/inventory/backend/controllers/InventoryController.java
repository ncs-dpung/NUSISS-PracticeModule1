package com.nusiss.inventory.backend.controllers;

import com.nusiss.inventory.backend.dto.ProductDto;
import com.nusiss.inventory.backend.service.InventoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@Api(value = "Reorder Product")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/products-needing-reorder")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved products needing reorder list")
    })
    public ResponseEntity<List<ProductDto>> getProductsNeedingReorder() {
        List<ProductDto> products = inventoryService.findProductsNeedingReorder();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/reorder-product")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully reordered product")
    })
    public ResponseEntity<String> reorderProduct(Long productId, int quantity) {
        inventoryService.reorderProduct(productId, quantity);
        return ResponseEntity.ok("Product reordered successfully");
    }
}

