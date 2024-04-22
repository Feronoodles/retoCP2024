package com.example.demo.service;

import com.example.demo.dto.client.DtoClient;
import com.example.demo.dto.client.DtoClientUpdate;
import com.example.demo.dto.product.DtoProduct;
import com.example.demo.dto.product.DtoProductUpdate;
import com.example.demo.entities.Client;
import com.example.demo.entities.Product;
import com.example.demo.persistence.ClientRepository;
import com.example.demo.persistence.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements IClientService {

    ClientRepository clientRepository;
    @Override
    public Client save(DtoClient dtoClient) {
        Client client = new Client(dtoClient);
        return clientRepository.save(client);
    }

    @Override
    @Transactional
    public Client updateClient(DtoClientUpdate dtoClientUpdate) {
        Client client = clientRepository.getReferenceById(dtoClientUpdate.getClient_id());
        client.updateProduct(dtoClientUpdate);
        return client;
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.getReferenceById(id);
    }

    @Override
    public Page<Client> findAll(Pageable pagination) {
        return (Page<Client>) clientRepository.findByStatus(pagination,true);
    }







}
