package com.emil.store_api.service;

import com.emil.store_api.entity.Product;
import com.emil.store_api.exception.ResourceNotFoundException;
import com.emil.store_api.exception.StoreApiException;
import com.emil.store_api.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private ProductRepository repository;
    private ProductService service;

    @BeforeEach
    void setUp() {
        repository = mock(ProductRepository.class);
        service = new ProductService(repository);
    }

    @Test
    void testSave() {
        Product product = new Product();
        when(repository.save(product)).thenReturn(product);

        Product saved = service.save(product);

        assertEquals(product, saved);
        verify(repository).save(product);
    }

    @Test
    void testFindProductFound() {
        Product product = new Product();
        product.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(product));

        Product found = service.findProduct(1L);

        assertEquals(product, found);
        verify(repository).findById(1L);
    }

    @Test
    void testFindProductNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.findProduct(1L));
    }

    @Test
    void testListAllProducts() {
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(repository.findAll()).thenReturn(products);

        List<Product> result = service.listAllProducts();

        assertEquals(2, result.size());
        verify(repository).findAll();
    }

    @Test
    void testGetPaginatedProducts() {
        Page<Product> page = new PageImpl<>(List.of(new Product()));
        when(repository.findAll(PageRequest.of(0, 5))).thenReturn(page);

        Page<Product> result = service.getPaginatedProducts(0, 5);

        assertEquals(1, result.getContent().size());
        verify(repository).findAll(PageRequest.of(0, 5));
    }

    @Test
    void testChangePrice() {
        Product product = new Product();
        product.setId(1L);
        product.setPrice(new BigDecimal("10.00"));
        when(repository.findById(1L)).thenReturn(Optional.of(product));
        when(repository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Product updated = service.changePrice(1L, new BigDecimal("20.00"));

        assertEquals(new BigDecimal("20.00"), updated.getPrice());
        verify(repository).save(product);
    }

    @Test
    void testDeleteProductFound() {
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);

        service.deleteProduct(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void testDeleteProductNotFound() {
        when(repository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> service.deleteProduct(1L));
        verify(repository, never()).deleteById(anyLong());
    }

    @Test
    void testSellProductSuccess() {
        Product product = new Product();
        product.setId(1L);
        product.setQuantity(10);
        when(repository.findById(1L)).thenReturn(Optional.of(product));
        when(repository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Product result = service.sellProduct(1L, 4);

        assertEquals(6, result.getQuantity());
        verify(repository).save(product);
    }

    @Test
    void testSellProductInsufficientStock() {
        Product product = new Product();
        product.setId(1L);
        product.setQuantity(2);
        when(repository.findById(1L)).thenReturn(Optional.of(product));

        assertThrows(StoreApiException.class, () -> service.sellProduct(1L, 5));
        verify(repository, never()).save(any());
    }
}
