package com.example.demo.persistence;

import com.example.demo.entities.Client;
import com.example.demo.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
    public Page<Order> findByStatus(Pageable paginacion, boolean status);

    public Page<Order> findByStatusAndProductId(Pageable paginacion, boolean status,Long productId);
}
