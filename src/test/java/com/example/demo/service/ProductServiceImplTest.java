package com.example.demo.service;

import com.example.demo.dto.product.DtoProduct;
import com.example.demo.entities.Client;
import com.example.demo.entities.Product;
import com.example.demo.persistence.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ProductServiceImplTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;
    @Mock
    private Page<Product> productPage;
    @Mock
    private Pageable pageableMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        product = new Product();
        product.setProduct_id(1l);
        product.setName("Coca-Cola 3lts");
        product.setPrice(10);
        product.setStatus(true);

    }

    @Test
    void save() {
        when(productRepository.save(any(Product.class))).thenReturn(product);

        assertNotNull(productService.save(new DtoProduct()));
    }

    @Test
    void findAll() {

        when(productRepository.findByStatus(pageableMock,true)).thenReturn(productPage);
        assertNotNull(productService.findAll(pageableMock));

    }
}