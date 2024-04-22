package com.example.demo.service;

import com.example.demo.dto.client.DtoClient;
import com.example.demo.dto.client.DtoClientUpdate;
import com.example.demo.dto.order.DtoOrder;
import com.example.demo.dto.order.DtoOrderUpdate;
import com.example.demo.entities.Client;
import com.example.demo.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderService {
    public Order save(DtoOrder dtoOrder);
    public Order updateOrder(DtoOrderUpdate dtoOrderUpdate);
    public Order findById(Long id);
    public Page<Order> findAll(Pageable pagination);
    public Page<Order> findAllByProduct(Pageable pagination,Long product_id);
}
