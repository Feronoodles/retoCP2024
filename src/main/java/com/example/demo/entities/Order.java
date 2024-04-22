package com.example.demo.entities;

import com.example.demo.dto.order.DtoOrder;
import com.example.demo.dto.order.DtoOrderUpdate;
import com.example.demo.dto.product.DtoProduct;
import com.example.demo.dto.product.DtoProductUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "orders")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orders_id;
    @Column(name = "product_id",nullable = false)
    private Long productId;

    @Column(nullable = false)
    private int cantidad;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id",nullable = false)
    private Client client;

    private boolean status;

    public Order(DtoOrder order)
    {
        this.productId = order.getProduct_id();
        this.cantidad = order.getCantidad();
        this.client = order.getClient();
        this.status = true;
    }

    public void deleteOrder()
    {
        this.status = false;
    }
    public void updateOrder(DtoOrderUpdate dtoOrderUpdate)
    {
        if(dtoOrderUpdate.getProduct_id()!=null && dtoOrderUpdate.getProduct_id()>0)
            this.productId = dtoOrderUpdate.getProduct_id();
        if(dtoOrderUpdate.getCantidad()>0)
            this.cantidad = dtoOrderUpdate.getCantidad();
        this.status = dtoOrderUpdate.isStatus();

    }
}
