package com.example.demo.service;

import com.example.demo.dto.client.DtoClient;
import com.example.demo.dto.order.DtoOrder;
import com.example.demo.entities.Client;
import com.example.demo.entities.Order;
import com.example.demo.persistence.ClientRepository;
import com.example.demo.persistence.OrderRepository;
import org.aspectj.weaver.ast.Or;
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

class OrderServiceImplTest {
    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Client client;
    private Order order;
    @Mock
    private Page<Order> orderPage;
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

        order = new Order();
        order.setOrders_id(1l);
        order.setCantidad(4);
        order.setProductId(1l);
        order.setClient(client);
        order.setStatus(true);
    }

    @Test
    void save() {
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        assertNotNull(orderService.save(new DtoOrder()));
    }

    @Test
    void findAll() {
        when(orderRepository.findByStatus(pageableMock,true)).thenReturn(orderPage);
        assertNotNull(orderService.findAll(pageableMock));
    }
}