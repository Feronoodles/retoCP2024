package com.example.demo.persistence;

import com.example.demo.entities.Client;
import com.example.demo.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
    public Page<Client> findByStatus(Pageable paginacion, boolean status);
}
