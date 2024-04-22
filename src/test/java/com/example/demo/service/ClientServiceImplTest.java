package com.example.demo.service;

import com.example.demo.dto.client.DtoClient;
import com.example.demo.dto.product.DtoProduct;
import com.example.demo.entities.Client;
import com.example.demo.entities.Product;
import com.example.demo.persistence.ClientRepository;
import com.example.demo.persistence.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ClientServiceImplTest {
    @Mock
    ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    private Client client;
    @Mock
    private Page<Client> clientPage;
    @Mock
    private Pageable pageableMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        client = new Client();
        client.setClient_id(1l);
        client.setFirst_names("fernando alexander");
        client.setLast_names("urlich espinoza");
        client.setAddress("calle antares 234");
        client.setStatus(true);
    }

    @Test
    void save() {
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        assertNotNull(clientService.save(new DtoClient()));
    }

    @Test
    void findAll() {
        when(clientRepository.findByStatus(pageableMock,true)).thenReturn(clientPage);
        assertNotNull(clientService.findAll(pageableMock));

    }
}