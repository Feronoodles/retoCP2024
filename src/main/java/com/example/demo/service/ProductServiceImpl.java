package com.example.demo.service;

import com.example.demo.dto.product.DtoProduct;
import com.example.demo.dto.product.DtoProductUpdate;
import com.example.demo.dto.user.DtoUser;
import com.example.demo.dto.user.DtoUserLogin;
import com.example.demo.entities.Product;
import com.example.demo.persistence.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService{
    ProductRepository productRepository;

    @Override
    public Product save(DtoProduct dtoProduct) {
        Product product = new Product(dtoProduct);
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product updateProduct(DtoProductUpdate dtoProductUpdate) {

        Product product = productRepository.getReferenceById(dtoProductUpdate.getProduct_id());
        product.updateProduct(dtoProductUpdate);
        return product;
    }

    @Override
    public Product findById(Long id) {
        return productRepository.getReferenceById(id);
    }

    @Override
    public Page<Product> findAll(Pageable pagination) {
        return (Page<Product>)productRepository.findByStatus(pagination,true);
    }


}
