package com.example.demo.dto.order;

import com.example.demo.entities.Client;
import com.example.demo.entities.Order;
import com.example.demo.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DtoOrderView {
    private Long orders_id;
    private Long product_id;
    private int cantidad;
    private Client client;
    private boolean status;

    public DtoOrderView(Order order)
    {
        this.orders_id = order.getOrders_id();
        this.product_id = order.getProductId();
        this.cantidad = order.getCantidad();
        this.client = order.getClient();
        this.status = order.isStatus();
    }

}
