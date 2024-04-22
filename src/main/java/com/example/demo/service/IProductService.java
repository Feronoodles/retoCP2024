package com.example.demo.service;

import com.example.demo.dto.product.DtoProduct;
import com.example.demo.dto.product.DtoProductUpdate;
import com.example.demo.dto.user.DtoUser;
import com.example.demo.dto.user.DtoUserLogin;
import com.example.demo.entities.Product;
import com.example.demo.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {

    public Product save(DtoProduct dtoProduct);
    public Product updateProduct(DtoProductUpdate dtoProductUpdate);
    public Product findById(Long id);
    public Page<Product> findAll(Pageable pagination);



}
