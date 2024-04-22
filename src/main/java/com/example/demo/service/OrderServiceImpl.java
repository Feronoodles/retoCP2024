package com.example.demo.service;

import com.example.demo.dto.order.DtoOrder;
import com.example.demo.dto.order.DtoOrderUpdate;
import com.example.demo.entities.Order;
import com.example.demo.entities.Product;
import com.example.demo.persistence.OrderRepository;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements IOrderService{

    OrderRepository orderRepository;

    @Override
    public Order save(DtoOrder dtoOrder) {
        Order order = new Order(dtoOrder);
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order updateOrder(DtoOrderUpdate dtoOrderUpdate) {
        Order order = orderRepository.getReferenceById(dtoOrderUpdate.getOrders_id());
        order.updateOrder(dtoOrderUpdate);
        return order;
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.getReferenceById(id);
    }

    @Override
    public Page<Order> findAll(Pageable pagination) {
        return (Page<Order>)orderRepository.findByStatus(pagination,true);
    }

    @Override
    public Page<Order> findAllByProduct(Pageable pagination, Long product_id) {
        return (Page<Order>) orderRepository.findByStatusAndProductId(pagination,true,product_id);
    }
}
