package com.example.demo.service;

import com.example.demo.dto.client.DtoClient;
import com.example.demo.dto.client.DtoClientUpdate;
import com.example.demo.dto.product.DtoProduct;
import com.example.demo.dto.product.DtoProductUpdate;
import com.example.demo.entities.Client;
import com.example.demo.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IClientService {
    public Client save(DtoClient dtoClient);
    public Client updateClient(DtoClientUpdate dtoClientUpdate);
    public Client findById(Long id);
    public Page<Client> findAll(Pageable pagination);

}
