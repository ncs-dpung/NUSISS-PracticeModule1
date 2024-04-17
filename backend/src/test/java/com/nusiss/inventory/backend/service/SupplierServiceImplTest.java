package com.nusiss.inventory.backend.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nusiss.inventory.backend.dao.SupplierDao;
import com.nusiss.inventory.backend.dto.SupplierDto;
import com.nusiss.inventory.backend.entity.Supplier;
import com.nusiss.inventory.backend.repository.ProductRepository;
import com.nusiss.inventory.backend.repository.SupplierRepository;
import com.nusiss.inventory.backend.service.impl.SupplierServiceImpl;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SupplierServiceImplTest {
    @Mock
    private SupplierRepository supplierRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private SupplierDao supplierDao;
    @Mock
    private ObjectMapper objectMapper;

    private SupplierServiceImpl supplierService;

    @BeforeEach
    public void setUp() {
        supplierService = new SupplierServiceImpl(supplierRepository, productRepository, supplierDao, objectMapper);
    }

    @Test
    void testGetSupplierById_Found() {
        Long id = 1L;
        Supplier supplier = mock(Supplier.class);
        SupplierDto supplierDto = new SupplierDto();

        when(supplierRepository.findById(id)).thenReturn(Optional.of(supplier));
        when(supplier.toDto()).thenReturn(supplierDto);

        SupplierDto result = supplierService.getSupplierById(id);

        assertNotNull(result);
        verify(supplierRepository).findById(id);
        verify(supplier).toDto();
    }


    @Test
    void testGetSupplierById_NotFound() {
        Long id = 1L;
        when(supplierRepository.findById(id)).thenReturn(Optional.empty());

        SupplierDto result = supplierService.getSupplierById(id);

        assertNull(result);
    }

    @Test
    void testUpdateSupplier_Success() throws JsonMappingException {
        Long id = 1L;
        SupplierDto supplierDto = mock(SupplierDto.class);
        Supplier supplier = mock(Supplier.class);

        when(supplierRepository.findById(id)).thenReturn(Optional.of(supplier));

        when(objectMapper.updateValue(eq(supplier), any())).thenReturn(supplier);
        when(supplierDao.saveSupplier(supplier)).thenReturn(supplier);
        when(supplier.toDto()).thenReturn(supplierDto);

        SupplierDto result = supplierService.updateSupplier(id, supplierDto);

        assertNotNull(result);
        verify(supplierRepository).findById(id);
        verify(supplierDao).saveSupplier(supplier);
    }


    @Test
    void testUpdateSupplier_NotFound() {
        Long id = 1L;
        SupplierDto supplierDto = new SupplierDto();
        when(supplierRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> supplierService.updateSupplier(id, supplierDto));
    }

    @Test
    void testDeleteSupplierById_NoProducts() {
        Long id = 1L;
        when(productRepository.countBySupplierId(id)).thenReturn(0L);

        assertDoesNotThrow(() -> supplierService.deleteSupplierById(id));
        verify(supplierRepository).deleteById(id);
    }

    @Test
    void testDeleteSupplierById_HasProducts() {
        Long id = 1L;
        when(productRepository.countBySupplierId(id)).thenReturn(10L);

        assertThrows(IllegalStateException.class, () -> supplierService.deleteSupplierById(id));
    }

    @Test
    void testGetAllSupplier() {
        // Setup mock suppliers
        Supplier supplier1 = new Supplier();
        supplier1.setId(1L);
        supplier1.setSupplierName("Supplier One");
        supplier1.setSupplierContact("Contact Info");
        supplier1.setSupplierAddress("Address Info");

        Supplier supplier2 = new Supplier();
        supplier2.setId(2L);
        supplier2.setSupplierName("Supplier Two");
        supplier2.setSupplierContact("Contact Info 2");
        supplier2.setSupplierAddress("Address Info 2");

        // Mock the repository to return a list of these suppliers
        when(supplierRepository.findAll()).thenReturn(Arrays.asList(supplier1, supplier2));

        // Execute the method under test
        List<SupplierDto> result = supplierService.getAllSupplier();

        // Assertions
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.stream().noneMatch(Objects::isNull)); // Ensure no null Dto
        result.forEach(dto -> assertNotNull(dto.getId())); // Ensure IDs are not null
        verify(supplierRepository).findAll();
    }



}
